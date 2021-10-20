package br.com.vhclaw.timesheet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vhclaw.timesheet.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
