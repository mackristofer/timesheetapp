package br.com.vhclaw.timesheet.resources;

import java.net.URI;
import java.util.List;

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

import br.com.vhclaw.timesheet.DTO.UsuarioDTO;
import br.com.vhclaw.timesheet.DTO.UsuarioInsertDTO;
import br.com.vhclaw.timesheet.DTO.UsuarioUpdateDTO;
import br.com.vhclaw.timesheet.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	

	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
			List<UsuarioDTO> list = service.findAll();
			return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> finById(@PathVariable Long id) {
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioInsertDTO dto) {
		UsuarioDTO newdto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioUpdateDTO dto) {
		UsuarioDTO newdto = service.update(id, dto);
		
		return ResponseEntity.ok(newdto);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	

}
