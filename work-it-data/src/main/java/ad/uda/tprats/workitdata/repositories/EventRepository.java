package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> getEventsByUser(User user);
    List<Event> getEventsByProject(Project project);
}
