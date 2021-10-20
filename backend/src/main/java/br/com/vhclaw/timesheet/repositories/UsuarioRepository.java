package br.com.vhclaw.timesheet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vhclaw.timesheet.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsuario(String usuario);

}
