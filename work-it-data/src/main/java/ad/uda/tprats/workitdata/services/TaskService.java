package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Task;
import ad.uda.tprats.workitdata.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    // READ
    public Task getTaskById(Long taskId) {
        return taskRepository.getById(taskId);
    }

    // DELETE
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // UPDATE
    public Task updateTask(Long taskId, Task details) {
        Task task= taskRepository.findById(taskId).get();
        task.setDescription(details.getDescription());
        task.setTitle(details.getTitle());
        task.setEstimatedHours(details.getEstimatedHours());
        task.setActive(details.isActive());
        task.setUser(details.getUser());
        return taskRepository.save(task);
    }
}
