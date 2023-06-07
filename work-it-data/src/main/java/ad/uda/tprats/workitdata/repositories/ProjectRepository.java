package ad.uda.tprats.workitdata.repositories;

import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project getProjectByTitle(String title);

    List<Project> getProjectsByCollaboratorsContaining(User user);
}
