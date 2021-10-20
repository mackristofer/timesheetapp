package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vhclaw.timesheet.entities.Categoria;

public class CategoriaDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank (message = "O campo descricao nao pode ser em branco")
	private String descricao;
	@NotNull (message = "O valor da hora nao poder ser em branco")
	//@Positive (message = "O valor da hora deve ser positivo")
	private Double valorHora;
	
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Long id, String descricao, Double valorHora) {
		this.id = id;
		this.descricao = descricao;
		this.valorHora = valorHora;
	}
	
	public CategoriaDTO(Categoria entity) {
		id = entity.getId();
		descricao = entity.getDescricao();
		valorHora = entity.getValorHora();
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

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valor) {
		this.valorHora = valor;
	}
	
	

}
