package br.com.vhclaw.timesheet.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.Cliente;

public interface CasoRepository extends JpaRepository<Caso, Long>{
	
	@Query("SELECT obj FROM Caso obj WHERE obj.cliente IN :cliente")
	Set<Caso> findCasos(Cliente cliente);

}
