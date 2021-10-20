package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;

import br.com.vhclaw.timesheet.entities.Caso;

public class CasoTSDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

	public CasoTSDTO() {

	}

	public CasoTSDTO(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public CasoTSDTO(Caso entity) {
		id = entity.getId();
		descricao = entity.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
