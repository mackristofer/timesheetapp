package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.TimeSheet;

public class CasoDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank (message = "O campo descricao deve ser preenchido")
	private String descricao;
	//@NotNull (message = "O campo status deve ser preenchido")
	private Integer statusCobranca;
	@NotNull (message = "O valor não pode ser nulo")
	private Double valor;
	@NotNull (message = "O cliente deve ser informado")
	private ClienteOnlyDTO cliente;
	@NotNull (message = "O tipo de contrato deve ser informado")
	private Integer tipoContrato;
	private Double desconto;
	
	Set<TimeSheetDTO> timesheets = new HashSet<>();
	
	public CasoDTO() {
		
	}

	public CasoDTO(Long id, String descricao, Integer statusCobranca, Double valor, ClienteOnlyDTO cliente, Integer tipoContrato, Double desconto) {
		this.id = id;
		this.descricao = descricao;
		this.statusCobranca= statusCobranca;
		this.valor = valor;
		this.cliente = cliente;
		this.tipoContrato = tipoContrato;
		this.desconto = desconto;
	}
	
	public CasoDTO(Caso entity) {
		id = entity.getId();
		descricao = entity.getDescricao();
		statusCobranca = entity.getStatusCobranca();
		valor = entity.getValor();
		cliente = new ClienteOnlyDTO(entity.getCliente());
		tipoContrato = entity.getTipoContrato();
		desconto = entity.getDesconto();
	//	entity.getTimeSheets().forEach(timesheet -> this.timesheet.add(new TimeSheetDTO(timesheet)));
		}
	
	public CasoDTO(Caso entity, List<TimeSheet> timesheets) {
		
		this(entity);
		
		timesheets.forEach(timesheet -> this.timesheets.add(new TimeSheetDTO(timesheet)));
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

	public Integer getStatusCobranca() {
		return statusCobranca;
	}

	public void setStatusCobranca(Integer statusCobranca) {
		this.statusCobranca = statusCobranca;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public ClienteOnlyDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteOnlyDTO cliente) {
		this.cliente = cliente;
	}

	//public Set<TimeSheetDTO> getTimesheet() {
	//	return timesheet;
	//}

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
	
	
	

}
