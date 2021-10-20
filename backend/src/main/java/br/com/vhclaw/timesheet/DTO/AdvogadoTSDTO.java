package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;

import br.com.vhclaw.timesheet.entities.Advogado;

public class AdvogadoTSDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;


	public AdvogadoTSDTO() {

	}

	public AdvogadoTSDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public AdvogadoTSDTO(Advogado entity) {
		id = entity.getId();
		nome = entity.getNome();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
