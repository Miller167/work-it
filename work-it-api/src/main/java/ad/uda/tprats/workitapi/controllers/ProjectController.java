package ad.uda.tprats.workit.workitapi.controllers;

import ad.uda.tprats.workit.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public List<Project> getAllProjects(){
        try {
            return projectService.getProjects();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId){
        try {
            return projectService.getProjectById(projectId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Project createProject(@RequestBody Project project) {
        try {
        return projectService.createProject(project);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project newProject){
        try {
            Project dbProject = projectService.getProjectById(projectId);
            if (dbProject == null){
                throw new CustomErrorException("No existe");
            }
            return ResponseEntity.ok(projectService.updateProject(dbProject, newProject));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/updateCollaborators/{projectId}")
    public ResponseEntity<Project> updateProjectCollaborators(@PathVariable Long projectId, @RequestBody List<User> collaborators){
        try {
            Project dbProject = projectService.getProjectById(projectId);
            if (dbProject == null){
                throw new CustomErrorException("Project does not exist");
            }
            return ResponseEntity.ok(projectService.updateProjectCollaborators(dbProject, collaborators));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/updateManager/{projectId}")
    public ResponseEntity<Project> updateProjectManager(@PathVariable Long projectId, @RequestBody User manager){
        try {
            Project dbProject = projectService.getProjectById(projectId);
            if (dbProject == null){
                throw new CustomErrorException("Project does not exist");
            }
            return ResponseEntity.ok(projectService.updateProjectManager(dbProject, manager));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }
}
