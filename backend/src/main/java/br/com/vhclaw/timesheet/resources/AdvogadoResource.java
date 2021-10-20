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

import br.com.vhclaw.timesheet.DTO.AdvogadoDTO;
import br.com.vhclaw.timesheet.DTO.AdvogadoTSDTO;
import br.com.vhclaw.timesheet.services.AdvogadoService;

@RestController
@RequestMapping(value = "/advogado")
public class AdvogadoResource {
	
	@Autowired
	private AdvogadoService service;
	
	@GetMapping
	public ResponseEntity<List<AdvogadoDTO>> findAll(){
			List<AdvogadoDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/tsa")
	public ResponseEntity<List<AdvogadoTSDTO>> findAllTs(){
			List<AdvogadoTSDTO> list = service.findAllTs();
			return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdvogadoDTO> finById(@PathVariable Long id) {
		AdvogadoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<AdvogadoDTO> insert(@Valid @RequestBody AdvogadoDTO dto) {
		AdvogadoDTO newdto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AdvogadoDTO> update(@PathVariable Long id, @Valid @RequestBody AdvogadoDTO dto) {
		AdvogadoDTO newdto = service.update(id, dto);
		
		return ResponseEntity.ok(newdto);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AdvogadoDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	

}
