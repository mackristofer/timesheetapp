package br.com.vhclaw.timesheet.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vhclaw.timesheet.DTO.RelatorioDTO;
import br.com.vhclaw.timesheet.services.RelatorioService;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioResource {
	@Autowired
	private RelatorioService service;
	
	
	@GetMapping(value = "/{id}/{dtIn}/{dtFn}")
	public ResponseEntity<RelatorioDTO> findDate(@PathVariable Long id, @PathVariable String dtIn, @PathVariable String dtFn){
		RelatorioDTO dto = service.findDate(id, dtIn, dtFn);
		return ResponseEntity.ok(dto);
	}
	
}
