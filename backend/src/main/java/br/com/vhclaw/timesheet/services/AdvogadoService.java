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

import br.com.vhclaw.timesheet.DTO.AdvogadoDTO;
import br.com.vhclaw.timesheet.DTO.AdvogadoTSDTO;
import br.com.vhclaw.timesheet.entities.Advogado;
import br.com.vhclaw.timesheet.entities.Categoria;
import br.com.vhclaw.timesheet.repositories.AdvogadoRepository;
import br.com.vhclaw.timesheet.repositories.CategoriaRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class AdvogadoService {
	
	@Autowired
	private AdvogadoRepository repository;
	
	@Autowired
	private CategoriaRepository catRep;
	

	@Transactional(readOnly = true)
	public List<AdvogadoDTO> findAll() {
		List<Advogado> list = repository.findAll();
		return list.stream().map(x -> new AdvogadoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<AdvogadoTSDTO> findAllTs() {
		List<Advogado> list = repository.findAll();
		return list.stream().map(x -> new AdvogadoTSDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AdvogadoDTO findById(Long id) {
		Optional<Advogado> obj = repository.findById(id);
		Advogado entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new AdvogadoDTO(entity);
	}
	
	
	@Transactional
	public AdvogadoDTO insert(AdvogadoDTO dto) {
		Advogado entity = new Advogado();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new AdvogadoDTO(entity);
	}
	
	@Transactional
	public AdvogadoDTO update(Long id, AdvogadoDTO dto) {
		try {
			Advogado entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new AdvogadoDTO(entity);
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
	
	private void copyDtoToEntity(AdvogadoDTO dto, Advogado entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setEndereco(dto.getEndereco());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setUf(dto.getUf());
		entity.setCep(dto.getCep());
		entity.setTelefone(dto.getTelefone());
		Categoria categoria = catRep.getOne(dto.getCategoria().getId());
		entity.setCategoria(categoria);

	}



}
