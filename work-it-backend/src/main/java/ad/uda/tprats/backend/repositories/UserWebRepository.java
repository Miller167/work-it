package ad.uda.tprats.backend.repositories;

import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWebRepository extends JpaRepository<User, Long>, DataTablesRepository<User, Long> {
	
	public User findById(long id);
	
	/*@Query("SELECT MAX(id) FROM User t")
	public Long getMaxId();*/

	
}
