package br.com.vhclaw.timesheet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vhclaw.timesheet.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
