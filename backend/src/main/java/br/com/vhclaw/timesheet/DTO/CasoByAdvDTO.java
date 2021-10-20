package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CasoByAdvDTO implements Serializable {
	/*
	 * Essa classe formata a lista de timesheet por advogado rotulando a qual caso ela pertence.
	 */

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String totalHoraCaso;
	
	private Double valorTotalCaso;
	
	private List<SheetByAdvDTO> sheetByAdv = new ArrayList<>();
	
	public CasoByAdvDTO(String caso, Set<String> adv, List<TimeSheetDTO> dto, Double valorContrato, Integer tipoContrato, Double desconto) {
		this.nome = caso;
		this.valorTotalCaso = valorContrato;
		orderByAdv(adv, dto, valorContrato, tipoContrato, desconto);
	}

	public List<SheetByAdvDTO> getSheetByAdv() {
		return sheetByAdv;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTotalHoraCaso(String totalHoraCaso) {
		this.totalHoraCaso = totalHoraCaso;
	}
	
	public String getTotalHoraCaso() {
		return totalHoraCaso;
	}

	public Double getValorTotalCaso() {
		return valorTotalCaso;
	}

	public void setValorTotalCaso(Double valorTotalCaso) {
		this.valorTotalCaso = valorTotalCaso;
	}

	public void orderByAdv(Set<String> adv, List<TimeSheetDTO> dto, Double valorContrato, Integer tipoContrato, Double desconto) {
		
		for(String s : adv) {
			SheetByAdvDTO sba = new SheetByAdvDTO();
			int totalHora = 0;
			double valor = 0;
			sba.setNomeAdv(s);
			for(TimeSheetDTO t : dto) {
				if(t.getAdvogado().getNome().equals(s) && t.getCaso().getDescricao().equals(nome)) {
				sba.getTimeSheet().add(new TimeSheetRelatorioDTO(t));	
				totalHora = totalHora + stringToTime(t.getTempo());
				valor = t.getAdvogado().getCategoria().getValorHora();
				}
			}
			sba.setMinutos(totalHora);
			sba.setHoraF(totalHora);
			sba.setTipo(tipoContrato);
			
			if(valorContrato == 0) {
				if(desconto != null) {
					sba.setValorHora(valor = valor - (valor * desconto / 100)); 	
				}else {
				sba.setValorHora(valor); 
				}
			}else {
				if(desconto != null) {
					sba.setValorHora(valorContrato = valorContrato - (valorContrato * desconto / 100));
				}else {
				sba.setValorHora(valorContrato);
				}
			}
					
			sba.getHoraF();
			//sba.getValorTotalHorasAdvogado(tipoContrato);
			
			if(!sba.getTimeSheet().isEmpty()) {
				//sheetByAdv = new ArrayList<>();
			sheetByAdv.add(sba);
			}
			
		}
		
		Integer horaTotal = 0;
		Double vlr = 0.0;
		for(SheetByAdvDTO x : sheetByAdv) {
			
			horaTotal += x.getMinutos();
			
			if(tipoContrato == 0) {
				vlr += x.getValorTotalHorasAdvogado();
			}else {
				vlr = x.getValorTotalHorasAdvogado();
				x.setValorHora(null);
				x.setValorTotalHorasAdvogado(null);
			}
			
			
		}
		
		totalHoraCaso = timeToString(horaTotal);
		valorTotalCaso = vlr;
		
	}
	
	public static String timeToString(Integer tempo) {
		int min = tempo % 60;
		int hor = (tempo - min) / 60;
		
		return hor+":"+min;
	}
	
	public static int stringToTime(String tempo)
	{
	    String[] parts = tempo.split(":");

	    int hour = Integer.parseInt(parts[0]);
	    int min = Integer.parseInt(parts[1]);

	    return (hour * 60) + min;
	}

	
	

}
