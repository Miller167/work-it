package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project getProjectByTitle(String title);
}
