package br.com.vhclaw.timesheet.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vhclaw.timesheet.DTO.TimeSheetDTO;
import br.com.vhclaw.timesheet.services.TimeSheetService;

@RestController
@RequestMapping(value = "/timesheet")
public class TimeSheetResource {
	
	@Autowired
	private TimeSheetService service;
	
	@GetMapping
	public ResponseEntity<List<TimeSheetDTO>> findAll() {
		
		List<TimeSheetDTO> list = service.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/byadv/{id}")
	public ResponseEntity<List<TimeSheetDTO>> findAllByAdv(@PathVariable Long id) {
	
		
		List<TimeSheetDTO> list = service.findAllByAdv(id);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TimeSheetDTO> finById(@PathVariable Long id) {
		TimeSheetDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<TimeSheetDTO> insert(@RequestBody TimeSheetDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TimeSheetDTO> update(@PathVariable Long id, @Valid @RequestBody TimeSheetDTO dto) {
		dto = service.update(id, dto);
		
		return ResponseEntity.ok(dto);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TimeSheetDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	

}
