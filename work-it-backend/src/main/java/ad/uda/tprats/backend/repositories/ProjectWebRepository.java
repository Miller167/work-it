package ad.uda.tprats.backend.repositories;

import ad.uda.tprats.workitdata.entities.Project;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectWebRepository extends JpaRepository<Project, Long>, DataTablesRepository<Project, Long> {
	
	public Project findById(long id);
	
	/*@Query("SELECT MAX(id) FROM Project t")
	public Long getMaxId();*/

	
}
