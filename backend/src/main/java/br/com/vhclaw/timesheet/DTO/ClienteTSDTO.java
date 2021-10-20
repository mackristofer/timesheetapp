package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.vhclaw.timesheet.entities.Cliente;

public class ClienteTSDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Long id;
    private String razao;



	private Set<CasoTSDTO> casos = new HashSet<>();
	
	public ClienteTSDTO() {
		
	}

	public ClienteTSDTO(Long id, String nome) {
		this.id = id;
		this.razao = nome;

	}
	
	public ClienteTSDTO(Cliente entity) {
		id = entity.getId();
		razao = entity.getRazao();
		entity.getCasos().forEach(x -> this.casos.add(new CasoTSDTO(x)));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}


	public Set<CasoTSDTO> getCasos() {
		return casos;
	}
}
