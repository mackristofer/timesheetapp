package br.com.vhclaw.timesheet.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.vhclaw.timesheet.DTO.UsuarioUpdateDTO;
import br.com.vhclaw.timesheet.entities.Usuario;
import br.com.vhclaw.timesheet.repositories.UsuarioRepository;
import br.com.vhclaw.timesheet.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UsuarioUpdateDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UsuarioUpdateDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings ("unchecked")
		var uriVars = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long usrId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Usuario usuario = repository.findByUsuario(dto.getUsuario());
		
		if(usuario != null && usrId != usuario.getId()) {
			list.add(new FieldMessage("Usuario", "Usuario ja cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
