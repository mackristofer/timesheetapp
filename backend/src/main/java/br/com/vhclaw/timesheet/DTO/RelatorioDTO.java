package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	
	private LocalDate dtInicial;
	
	private LocalDate dtFinal;
	
	private Double valorTotalNota =0.0;
	
	private List<CasoByAdvDTO> casoByAdv = new ArrayList<>();
	
//	private Set<String> adv = new HashSet<>();
//	
//	private Set<String> caso = new HashSet<>();
//	
	
	public RelatorioDTO() {
		
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public LocalDate getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(LocalDate dtInicial) {
		this.dtInicial = dtInicial;
	}

	public LocalDate getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(LocalDate dtFinal) {
		this.dtFinal = dtFinal;
	}

	public List<CasoByAdvDTO> getCasoByAdv() {
		return casoByAdv;
	}
	
	public void setValorTotalNota(Double valorTotalNota) {
		this.valorTotalNota = valorTotalNota;
	}
	
	public Double getValorTotalNota() {
		for(CasoByAdvDTO c : casoByAdv) {
			valorTotalNota += c.getValorTotalCaso();
		}
		return valorTotalNota;
	}

//	public Set<String> getAdv() {
//		return adv;
//	}
//
//	public void setAdv(Set<String> adv) {
//		this.adv = adv;
//	}
//
//	public Set<String> getCaso() {
//		return caso;
//	}
//
//	public void setCaso(Set<String> caso) {
//		this.caso = caso;
//	}


}
