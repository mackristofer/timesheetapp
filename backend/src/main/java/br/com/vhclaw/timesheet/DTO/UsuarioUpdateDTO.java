package br.com.vhclaw.timesheet.DTO;

import br.com.vhclaw.timesheet.services.validation.UserUpdateValid;

@UserUpdateValid
public class UsuarioUpdateDTO extends UsuarioDTO{

	private static final long serialVersionUID = 1L;

	private String password;
	
	public UsuarioUpdateDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
