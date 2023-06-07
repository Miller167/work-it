package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.WorkPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPeriodRepository extends JpaRepository<WorkPeriod, Long> {
}
