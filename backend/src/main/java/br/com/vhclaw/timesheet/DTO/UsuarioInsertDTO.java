package br.com.vhclaw.timesheet.DTO;

import javax.validation.constraints.NotBlank;

import br.com.vhclaw.timesheet.services.validation.UserInsertValid;

@UserInsertValid
public class UsuarioInsertDTO extends UsuarioDTO{

	private static final long serialVersionUID = 1L;
	
	@NotBlank (message = "A senha deve ser preenchida")
	private String password;
	
	public UsuarioInsertDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
