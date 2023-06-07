package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.DaysOff;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaysOffRepository extends JpaRepository<DaysOff, Long> {
    List<DaysOff> getDaysOffsByUser(User user);
}
