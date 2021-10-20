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

import br.com.vhclaw.timesheet.DTO.CategoriaDTO;
import br.com.vhclaw.timesheet.entities.Categoria;
import br.com.vhclaw.timesheet.repositories.CategoriaRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll() {
		List<Categoria> list = repository.findAll();
		return list.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new CategoriaDTO(entity);
	}
	
	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}
	
	@Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			Categoria entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
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
	
	private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
		entity.setDescricao(dto.getDescricao());
		entity.setValorHora(dto.getValorHora());

	}

}
