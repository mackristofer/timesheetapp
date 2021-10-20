package br.com.vhclaw.timesheet.repositories;



import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vhclaw.timesheet.entities.Advogado;
import br.com.vhclaw.timesheet.entities.Caso;
import br.com.vhclaw.timesheet.entities.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{
	@Query("SELECT obj FROM TimeSheet obj WHERE obj.caso IN :caso")
	List<TimeSheet> find(Caso caso);
	
	@Query("SELECT obj FROM TimeSheet obj WHERE obj.caso IN :caso AND obj.advogado IN :adv")
	List<TimeSheet> findByAdv(Set<Advogado> adv, Set<Caso> caso);
	
	@Query("SELECT obj FROM TimeSheet obj WHERE obj.caso IN :casos AND obj.data >= :dtIn AND obj.data <= :dtFn")
	List<TimeSheet> findDate(Set<Caso> casos, LocalDate dtIn, LocalDate dtFn);

	//TimeSheet findByCaso(Caso c);
	
	@Query("SELECT obj FROM TimeSheet obj WHERE obj.advogado IN :adv")
	List<TimeSheet> findAllByAdv(Advogado adv);

}
