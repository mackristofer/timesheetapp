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

import br.com.vhclaw.timesheet.DTO.CategoriaDTO;
import br.com.vhclaw.timesheet.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
			List<CategoriaDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> finById(@PathVariable Long id) {
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
		dto = service.update(id, dto);
		
		return ResponseEntity.ok(dto);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
