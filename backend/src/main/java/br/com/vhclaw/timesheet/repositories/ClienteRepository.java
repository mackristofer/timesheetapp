package br.com.vhclaw.timesheet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vhclaw.timesheet.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
