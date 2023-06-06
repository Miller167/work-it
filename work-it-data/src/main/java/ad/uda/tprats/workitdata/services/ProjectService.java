package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // CREATE
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // READ
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    // READ
    public Project getProjectById(Long projectId) {
        return projectRepository.getById(projectId);
    }

    // READ
    public Project getProjectByTitle(String title) {
        return projectRepository.getProjectByTitle(title);
    }

    // DELETE
    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    // UPDATE
    public Project updateProject(Project dbProject, Project newProject) {
        dbProject.setTitle(newProject.getTitle());
        dbProject.setDescription(newProject.getDescription());
        dbProject.setEstimatedHours(newProject.getEstimatedHours());
        //dbProject.setTotalHours(newProject.getTotalHours()); //total hours should be calculated every time a task is done

        return projectRepository.save(dbProject);
    }

    public Project updateProjectCollaborators(Project dbProject, List<User> collaborators) {
        dbProject.setCollaborators(collaborators);
        return projectRepository.save(dbProject);
    }

    public Project updateProjectManager(Project dbProject, User manager) {
        dbProject.setManager(manager);
        return projectRepository.save(dbProject);
    }
}
