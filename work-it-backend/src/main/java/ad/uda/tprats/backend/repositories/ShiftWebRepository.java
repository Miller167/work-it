package ad.uda.tprats.backend.repositories;

import ad.uda.tprats.workitdata.entities.Shift;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ShiftWebRepository extends JpaRepository<Shift, Long>, DataTablesRepository<Shift, Long> {
	
	public Shift findById(long id);

	//public DataTablesOutput<Shift> getShiftsByStartDatetimeDateEquals(DataTablesInput var1, Specification<Date> date);
	
	/*@Query("SELECT MAX(id) FROM Shift t")
	public Long getMaxId();*/

	
}
