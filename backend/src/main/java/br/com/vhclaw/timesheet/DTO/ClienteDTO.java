package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import br.com.vhclaw.timesheet.entities.Cliente;

public class ClienteDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank (message = "O nome do cliente deve ser preenchido")
	private String razao;
	@NotBlank (message = "O campo cnpj/cpf deve ser preenchido")
	private String cnpjCpf;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	private String contato;
	private String obs;


	private Set<CasoDTO> casos = new HashSet<>();
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Long id, String nome, String cnpjCpf, String endereco, String complemento, String bairro,
			String cidade, String uf, String cep, String telefone, String contato, String obs) {
		this.id = id;
		this.razao = nome;
		this.cnpjCpf = cnpjCpf;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.telefone = telefone;
		this.contato = contato;
		this.obs = obs;
	}
	
	public ClienteDTO(Cliente entity) {
		id = entity.getId();
		razao = entity.getRazao();
		cnpjCpf = entity.getCnpjCpf();
		endereco = entity.getEndereco();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		cidade = entity.getCidade();
		uf = entity.getUf();
		cep = entity.getCep();
		telefone = entity.getTelefone();
		contato = entity.getContato();
		obs = entity.getObs();
		entity.getCasos().forEach(x -> this.casos.add(new CasoDTO(x)));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<CasoDTO> getCasos() {
		return casos;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

}
