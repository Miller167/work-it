package ad.uda.tprats.backend.services;

import ad.uda.tprats.backend.filter.FilterProject;
import ad.uda.tprats.backend.repositories.ProjectWebRepository;
import ad.uda.tprats.backend.repositories.ProjectWebRepository;
import ad.uda.tprats.workitdata.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ProjectWebService {

	@Autowired
    ProjectWebRepository projectWebRepository;

	public List<Project> findAll() {
		return projectWebRepository.findAll();
	}

    public Project findById(Long id) {
        return projectWebRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found: " + id.toString()));
    }
    
    public void saveProject(Project project) {
    	
    	Long projectId = project.getId();
    	
    	if(projectId == null || projectId == 0L) {
    		//GET NEW ID
    		//Long maxId = projectRepository.getMaxId();
    		//long newId = (maxId == null || maxId == 0) ? 1: maxId+1;
    		//project.setId(newId);
    	}

        projectWebRepository.save(project);
    }
    
    public DataTablesOutput<Project> findAll(@Valid DataTablesInput input) {

        return projectWebRepository.findAll(input, new FilterProject(input));
    }
    
    public boolean deleteProject(Long id) {

        Project project = findById(id);
        if (project != null) {
            projectWebRepository.delete(project);
            return true;
        }
        return false;
    }
}
