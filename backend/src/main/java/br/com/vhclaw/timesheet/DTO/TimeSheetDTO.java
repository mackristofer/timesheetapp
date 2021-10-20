package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vhclaw.timesheet.entities.TimeSheet;

public class TimeSheetDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull (message = "A data deve ser preenchida")
	private LocalDate dt;
	@NotBlank (message = "A tempo deve ser preenchido")
	private String tempo;
	@NotBlank (message = "A descricao deve ser preenchida")
	private String descricao;
	@NotNull (message = "Obrigatorio informar o advogado")
	private AdvogadoDTO advogado;
	@NotNull (message = "Obrigatorio informar o caso")
	private CasoDTO caso;
	@NotNull (message = "O campo cobranca deve ser preenchido")
	private Integer cobranca;
	
	
	public TimeSheetDTO() {
		
	}

	public TimeSheetDTO(Long id, LocalDate data, String tempo, String descricao, AdvogadoDTO advogado,
			CasoDTO caso, Integer cobranca) throws ParseException{
		this.id = id;
		this.dt = data;
		this.tempo = tempo;
		this.descricao = descricao;
		this.advogado = advogado;
		this.caso = caso;
		this.cobranca = cobranca;
	}
	
	public TimeSheetDTO(TimeSheet entity) {
		id = entity.getId();
		dt = entity.getData();
		tempo = entity.getTempo();
		descricao = entity.getDescricao();
		cobranca = entity.getCobranca();
		advogado = new AdvogadoDTO(entity.getAdvogado());
		caso = new CasoDTO(entity.getCaso());
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return dt;
	}

	public void setData(LocalDate data) {
		this.dt = data;
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

	public AdvogadoDTO getAdvogado() {
		return advogado;
	}

	public void setAdvogado(AdvogadoDTO advogado) {
		this.advogado = advogado;
	}

	public CasoDTO getCaso() {
		return caso;
	}

	public void setCaso(CasoDTO caso) {
		this.caso = caso;
	}

	public Integer getCobranca() {
		return cobranca;
	}

	public void setCobranca(Integer cobranca) {
		this.cobranca = cobranca;
	}

	
	

}
