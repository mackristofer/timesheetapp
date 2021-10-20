package br.com.vhclaw.timesheet.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_caso")
public class Caso implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private Double valor;
	
	private Integer statusCobranca;
	
	private Integer tipoContrato;
	
	private Double desconto;

	
	@OneToMany(mappedBy = "caso")
	private Set<TimeSheet> timeSheets = new HashSet<>();
	
	public Caso() {
		
	}

	public Caso(Long id, String descricao, Double valor, Integer statusCobranca, Cliente cliente, Integer tipoContrato, Double desconto) {
		this.id = id;
		this.descricao = descricao;
		this.cliente = cliente;
		this.valor = valor;
		this.statusCobranca = statusCobranca;
		this.tipoContrato = tipoContrato;
		this.desconto = desconto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<TimeSheet> getTimeSheets() {
		return timeSheets;
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
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getStatusCobranca() {
		return statusCobranca;
	}

	public void setStatusCobranca(Integer statusCobranca) {
		this.statusCobranca = statusCobranca;
	}
	
	

	public Integer getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(Integer tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caso other = (Caso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
