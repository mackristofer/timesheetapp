package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SheetByAdvDTO implements Serializable {
	/*
	 * Essa classe serpara a lista de timesheet pelo advogado
	 * alem de retornar o tempo total da lista de timesheet.
	 */

	private static final long serialVersionUID = 1L;
	
	private String nomeAdv;
	
    private Integer minutos;
	
	private String horaF;
	
	private Double valorHora;
	
	private Integer tipo;
	
	private Double valorTotalHorasAdvogado;
	
	private Set<TimeSheetRelatorioDTO> timeSheet = new HashSet<>();
	
	public SheetByAdvDTO() {

	}

	public String getNomeAdv() {
		return nomeAdv;
	}

	public void setNomeAdv(String nome) {
		this.nomeAdv = nome;
	}

	public Set<TimeSheetRelatorioDTO> getTimeSheet() {
		return timeSheet;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Double getValorTotalHorasAdvogado() {
		if(tipo == 0) {
			valorTotalHorasAdvogado = (valorHora * minutos) / 60;
			return valorTotalHorasAdvogado;		
		}else {
			valorTotalHorasAdvogado = valorHora;
			return valorTotalHorasAdvogado;	
		}
			
	}

	public void setValorTotalHorasAdvogado(Double valorTotalHorasAdvogado) {
		this.valorTotalHorasAdvogado = valorTotalHorasAdvogado;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}
	
	public String getHoraF() {
		return horaF;
	}
	
	public void setHoraF(Integer min) {
		horaF = timeToString(min);
	}

	public static String timeToString(Integer tempo) {
		int min = tempo % 60;
		int hor = (tempo - min) / 60;
		
		return hor+":"+min;
	}
	
}
