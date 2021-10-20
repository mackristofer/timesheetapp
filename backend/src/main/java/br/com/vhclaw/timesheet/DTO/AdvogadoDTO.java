package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vhclaw.timesheet.entities.Advogado;
import br.com.vhclaw.timesheet.entities.TimeSheet;

public class AdvogadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank (message = "O campo nome deve ser preenchido")
	private String nome;
	@NotNull (message = "O CPF deve ser preenchido")
	private String cpf;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	@NotNull
	private CategoriaDTO categoria;
	
	Set<TimeSheetDTO> timesheets = new HashSet<>();

	public AdvogadoDTO() {

	}

	public AdvogadoDTO(Long id, String nome, String cpf, String endereco, String complemento, String bairro,
			String cidade, String uf, String cep, String telefone, CategoriaDTO categoria) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.categoria = categoria;
	}

	public AdvogadoDTO(Advogado entity) {
		id = entity.getId();
		nome = entity.getNome();
		cpf = entity.getCpf();
		endereco = entity.getEndereco();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		cidade = entity.getCidade();
		uf = entity.getUf();
		cep = entity.getCep();
		telefone = entity.getTelefone();
		categoria = new CategoriaDTO(entity.getCategoria());
		//entity.getTimeSheets().forEach(timesheet -> this.timesheets.add(new TimeSheetDTO(timesheet)));
		}
	
	public AdvogadoDTO(Advogado entity, List<TimeSheet> timesheets) {
		
		this(entity);
		
		timesheets.forEach(timesheet -> this.timesheets.add(new TimeSheetDTO(timesheet)));
	}


	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}


	public Set<TimeSheetDTO> getTimesheets() {
		return timesheets;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
