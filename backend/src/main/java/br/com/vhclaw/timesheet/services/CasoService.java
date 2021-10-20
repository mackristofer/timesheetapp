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

import br.com.vhclaw.timesheet.DTO.CasoDTO;
import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.Cliente;
import br.com.vhclaw.timesheet.repositories.CasoRepository;
import br.com.vhclaw.timesheet.repositories.ClienteRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class CasoService {

	@Autowired
	private CasoRepository repository;
	
	@Autowired
	private ClienteRepository cliRep;

	@Transactional(readOnly = true)
	public List<CasoDTO> findAll() {
		List<Caso> list = repository.findAll();
		return list.stream().map(x -> new CasoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CasoDTO findById(Long id) {
		Optional<Caso> obj = repository.findById(id);
		Caso entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new CasoDTO(entity);
	}
	
	@Transactional
	public CasoDTO insert(CasoDTO dto) {
		Caso entity = new Caso();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new CasoDTO(entity);
	}
	
	@Transactional
	public CasoDTO update(Long id, CasoDTO dto) {
		try {
			Caso entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CasoDTO(entity);
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
	
	private void copyDtoToEntity(CasoDTO dto, Caso entity) {
		entity.setDescricao(dto.getDescricao());
		entity.setStatusCobranca(dto.getStatusCobranca());
		entity.setValor(dto.getValor());
		Cliente cliente = cliRep.getOne(dto.getCliente().getId());
		entity.setCliente(cliente);
		entity.setTipoContrato(dto.getTipoContrato());
		entity.setDesconto(dto.getDesconto());
	

	}

}
