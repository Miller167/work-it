package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findShiftByUser(User user);
}
