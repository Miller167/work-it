package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> { }
