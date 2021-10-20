package br.com.vhclaw.timesheet.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vhclaw.timesheet.DTO.CasoByAdvDTO;
import br.com.vhclaw.timesheet.DTO.RelatorioDTO;
import br.com.vhclaw.timesheet.DTO.TimeSheetDTO;
import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.Cliente;
import br.com.vhclaw.timesheet.entities.TimeSheet;
import br.com.vhclaw.timesheet.repositories.ClienteRepository;
import br.com.vhclaw.timesheet.repositories.TimeSheetRepository;

@Service
public class RelatorioService {

	@Autowired
	private ClienteRepository cliRep;

	@Autowired
	private TimeSheetRepository timeRep;
	//cliente

	private Cliente cliente;

	private Set<String> casosDto;

	private Set<String> advogadosDto;

	private List<TimeSheetDTO> timeSheetDto;

	@Transactional(readOnly = true)
	public RelatorioDTO findDate(Long id, String dtIn, String dtFn) {
		
		List<TimeSheet> timeSheet = new ArrayList<>();

		RelatorioDTO relatorio = new RelatorioDTO();

		cliente = cliRep.getOne(id);

		relatorio.setNomeCliente(cliente.getRazao());

		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate dt1 = LocalDate.parse(dtIn);
		relatorio.setDtInicial(dt1);
		LocalDate dt2 = LocalDate.parse(dtFn);
		relatorio.setDtFinal(dt2);

		timeSheet = timeRep.findDate(cliente.getCasos(), relatorio.getDtInicial(), relatorio.getDtFinal());
		
		convertDto(timeSheet);
		
		getListAdv(timeSheetDto);
		
		getListCasos(timeSheetDto);

		for(String s : casosDto) {
			Double valor = null;
			Integer tipoContrato = null;
			Double desconto = null;
			for(Caso c : cliente.getCasos()) {
				if(c.getDescricao().equals(s)) {
				valor = c.getValor();
				tipoContrato = c.getTipoContrato();
				desconto = c.getDesconto();
				break;
				}
			}	
				relatorio.getCasoByAdv().add(new CasoByAdvDTO(s, advogadosDto, timeSheetDto, valor, tipoContrato, desconto));
		}

		return relatorio;
		
		

	}

	public void getListAdv(List<TimeSheetDTO> timeSheet) {
		advogadosDto = new HashSet<>();
		for (TimeSheetDTO t : timeSheet) {
			

			advogadosDto.add(t.getAdvogado().getNome());
		}

	}

	public void getListCasos(List<TimeSheetDTO> timeSheet) {
		casosDto = new HashSet<>();
		for (TimeSheetDTO t : timeSheet) {


			casosDto.add(t.getCaso().getDescricao());
		}
	}
	
	public void convertDto(List<TimeSheet> set) {
		timeSheetDto = new ArrayList<>();
		for(TimeSheet t : set) {

			timeSheetDto.add(new TimeSheetDTO(t));
		}
	}

}
