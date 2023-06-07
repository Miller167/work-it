package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Todo;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> getTodosByUser(User user);
}
