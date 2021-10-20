package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;

import br.com.vhclaw.timesheet.entities.Cliente;

public class ClienteOnlyDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Long id;
    private String razao;

	
	public ClienteOnlyDTO() {
		
	}

	public ClienteOnlyDTO(Long id, String nome) {
		this.id = id;
		this.razao = nome;

	}
	
	public ClienteOnlyDTO(Cliente entity) {
		id = entity.getId();
		razao = entity.getRazao();
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

}
