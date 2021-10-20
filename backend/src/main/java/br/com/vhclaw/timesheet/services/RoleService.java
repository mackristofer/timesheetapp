package br.com.vhclaw.timesheet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vhclaw.timesheet.DTO.RoleDTO;
import br.com.vhclaw.timesheet.entities.Role;
import br.com.vhclaw.timesheet.repositories.RoleRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class RoleService {

	
	@Autowired
	private RoleRepository repository;

	@Transactional(readOnly = true)
	public List<RoleDTO> findAll() {
		List<Role> list = repository.findAll();
		return list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		Optional<Role> obj = repository.findById(id);
		Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new RoleDTO(entity);
	}
	
	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role entity = new Role();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new RoleDTO(entity);
	}
	
	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new RoleDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(RoleDTO dto, Role entity) {
		entity.setAuthority(dto.getAuthority());

	}

}
