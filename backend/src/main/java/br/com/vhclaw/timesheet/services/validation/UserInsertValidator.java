package br.com.vhclaw.timesheet.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.vhclaw.timesheet.DTO.UsuarioInsertDTO;
import br.com.vhclaw.timesheet.entities.Usuario;
import br.com.vhclaw.timesheet.repositories.UsuarioRepository;
import br.com.vhclaw.timesheet.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioInsertDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Usuario usuario = repository.findByUsuario(dto.getUsuario());
		
		if(usuario != null) {
			list.add(new FieldMessage("Nome", "Usuário cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
