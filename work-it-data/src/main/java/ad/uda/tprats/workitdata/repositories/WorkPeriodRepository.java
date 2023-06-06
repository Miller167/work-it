package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.entities.WorkPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WorkPeriodRepository extends JpaRepository<WorkPeriod, Long> {
}
