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

import br.com.vhclaw.timesheet.DTO.TimeSheetDTO;
import br.com.vhclaw.timesheet.entities.Advogado;
import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.TimeSheet;
import br.com.vhclaw.timesheet.repositories.AdvogadoRepository;
import br.com.vhclaw.timesheet.repositories.CasoRepository;
import br.com.vhclaw.timesheet.repositories.TimeSheetRepository;
import br.com.vhclaw.timesheet.services.exception.DataBaseException;
import br.com.vhclaw.timesheet.services.exception.ResourceNotFoundException;

@Service
public class TimeSheetService {

	@Autowired
	private TimeSheetRepository repository;
	@Autowired
	private AdvogadoRepository advRep;
	@Autowired
	private CasoRepository casoRep;

	@Transactional(readOnly = true)
	public List<TimeSheetDTO> findAll() {
		List<TimeSheet> list = repository.findAll();
		return list.stream().map(x -> new TimeSheetDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<TimeSheetDTO> findAllByAdv(Long id) {
		Advogado adv = advRep.getOne(id);
		List<TimeSheet> list = repository.findAllByAdv(adv);
		return list.stream().map(x -> new TimeSheetDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TimeSheetDTO findById(Long id) {
		Optional<TimeSheet> obj = repository.findById(id);
		TimeSheet entity = obj.orElseThrow(() -> new ResourceNotFoundException("Object not found"));
		// TimeSheet entity = obj.get();
		return new TimeSheetDTO(entity);
	}
	
	

	@Transactional
	public TimeSheetDTO insert(TimeSheetDTO dto) {
		TimeSheet entity = new TimeSheet();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TimeSheetDTO(entity);
	}

	@Transactional
	public TimeSheetDTO update(Long id, TimeSheetDTO dto) {
		try {
			TimeSheet entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TimeSheetDTO(entity);
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

	private void copyDtoToEntity(TimeSheetDTO dto, TimeSheet entity) {
		entity.setData(dto.getData());
		entity.setTempo(dto.getTempo());
		entity.setDescricao(dto.getDescricao());
		Advogado advogado = advRep.getOne(dto.getAdvogado().getId());
		entity.setAdvogado(advogado);
		Caso caso = casoRep.getOne(dto.getCaso().getId());
		entity.setCaso(caso);
		entity.setCobranca(dto.getCobranca());

	}

}
