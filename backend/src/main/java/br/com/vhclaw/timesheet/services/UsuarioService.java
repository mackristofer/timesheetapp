package br.com.vhclaw.timesheet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vhclaw.timesheet.DTO.RoleDTO;
import br.com.vhclaw.timesheet.DTO.UsuarioDTO;
import br.com.vhclaw.timesheet.DTO.UsuarioInsertDTO;
import br.com.vhclaw.timesheet.DTO.UsuarioUpdateDTO;
import br.com.vhclaw.timesheet.entities.Advogado;
import br.com.vhclaw.timesheet.entities.Role;
import br.com.vhclaw.timesheet.entities.Usuario;
import br.com.vhclaw.timesheet.repositories.AdvogadoRepository;
import br.com.vhclaw.timesheet.repositories.RoleRepository;
import br.com.vhclaw.timesheet.repositories.UsuarioRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private AdvogadoRepository advRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		return new UsuarioDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
		
	}

	@Transactional
	public UsuarioDTO update(Long id, UsuarioUpdateDTO dto) {
		try {
			Usuario entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	@Transactional
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);

		return new UsuarioDTO(entity);
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {

		entity.setUsuario(dto.getUsuario());
		Advogado adv = advRepository.getOne(dto.getAdvogado().getId());
		entity.setAdvogado(adv);
		entity.getRoles().clear();

		for (RoleDTO roledto : dto.getRoles()) {
			Role role = roleRepository.getOne(roledto.getId());
			entity.getRoles().add(role);
		}

	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = repository.findByUsuario(username);
		if (user == null) {
			logger.error("Nome nao encontrado: " + username);
			throw new UsernameNotFoundException("Name not found");
		}
		logger.info("Usuario encontrado: " + username);
		return user;
	}

}
