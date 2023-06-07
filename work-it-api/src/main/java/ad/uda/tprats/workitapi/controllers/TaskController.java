package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.services.TaskService;
import ad.uda.tprats.workitdata.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public List<Task> getAllTasks() {
        try {
            return taskService.getTasks();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        try {
            return taskService.getTaskById(taskId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Task createTask(@RequestBody Task task) {
        try {
            return taskService.createTask(task);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.updateTask(taskId, task));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            if (task == null) {
                throw new CustomErrorException("Task does not exist");
            }
            taskService.deleteTask(taskId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}
