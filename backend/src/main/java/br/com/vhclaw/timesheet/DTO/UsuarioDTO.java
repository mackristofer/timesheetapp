package br.com.vhclaw.timesheet.DTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.vhclaw.timesheet.entities.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank (message = "O campo deve ser preenchido.")
	private String usuario;
	@NotNull (message = "Campo obrigatorio")
	private AdvogadoTSDTO advogado;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String usuario, AdvogadoTSDTO advogado) {
		this.id = id;
		this.usuario = usuario;
		this.advogado = advogado;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		usuario = entity.getUsuario();
		advogado = new AdvogadoTSDTO(entity.getAdvogado());
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public AdvogadoTSDTO getAdvogado() {
		return advogado;
	}

	public void setAdvogado(AdvogadoTSDTO advogado) {
		this.advogado = advogado;
	}
	
	

}
