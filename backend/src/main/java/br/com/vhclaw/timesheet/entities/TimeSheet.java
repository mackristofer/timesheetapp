package br.com.vhclaw.timesheet.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_timesheet")
public class TimeSheet implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@Temporal(TemporalType.DATE)
	private LocalDate data;

	//@Temporal(TemporalType.TIME)
	private String tempo;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "advogado_id")
	private Advogado advogado;
	
	@ManyToOne
	@JoinColumn(name = "caso_id")
	private Caso caso;
	
	private Integer cobranca;
	
	public TimeSheet() {
		
	}

	public TimeSheet(Long id, LocalDate data, String tempo, String descricao, Advogado advogado, Caso caso, Integer cobranca) {
		this.id = id;
		this.data = data;
		this.tempo = tempo;
		this.descricao = descricao;
		this.advogado = advogado;
		this.caso = caso;
		this.cobranca = cobranca;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	public Caso getCaso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}
	

	public Integer getCobranca() {
		return cobranca;
	}

	public void setCobranca(Integer cobranca) {
		this.cobranca = cobranca;
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
		TimeSheet other = (TimeSheet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
