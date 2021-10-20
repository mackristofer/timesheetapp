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
import br.com.vhclaw.timesheet.DTO.ClienteDTO;
import br.com.vhclaw.timesheet.DTO.ClienteTSDTO;
import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.Cliente;
import br.com.vhclaw.timesheet.repositories.CasoRepository;
import br.com.vhclaw.timesheet.repositories.ClienteRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private CasoRepository casoRepository;
	

	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll() {
		List<Cliente> list = repository.findAll();
		return list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ClienteTSDTO> findAllTs() {
		List<Cliente> list = repository.findAll();
		return list.stream().map(x -> new ClienteTSDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new ClienteDTO(entity);
	}
	
	@Transactional
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClienteDTO(entity);
	}
	
	@Transactional
	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {
			Cliente entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClienteDTO(entity);
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
	
	private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
		entity.setRazao(dto.getRazao());
		entity.setCnpjCpf(dto.getCnpjCpf());
		entity.setEndereco(dto.getEndereco());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setCidade(dto.getCidade());
		entity.setUf(dto.getUf());
		entity.setCep(dto.getCep());
		entity.setTelefone(dto.getTelefone());
		entity.setContato(dto.getContato());
		entity.setObs(dto.getObs());
		
		entity.getCasos().clear();
		
		for(CasoDTO casoDto : dto.getCasos()) {
			Caso caso = casoRepository.getOne(casoDto.getId());
			entity.getCasos().add(caso);
		}
		

	}

}
