package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.services.TodoService;
import ad.uda.tprats.workitdata.entities.Todo;
import ad.uda.tprats.workitdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Todo> getAllTodos() {
        try {
            return todoService.getTodos();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable Long todoId) {
        try {
            return todoService.getTodoById(todoId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/userId/{userId}")
    public List<Todo> getTodoByUserId(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("Todo does not exist");
            }
            return todoService.getTodosByUser(user);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Todo createTodo(@RequestBody Todo todo) {
        try {
            return todoService.createTodo(todo);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        try {
            return ResponseEntity.ok(todoService.updateTodo(todoId, todo));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    /*@PostMapping("/deleteTodo/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long todoId) {
        try {
            Todo todo = todoService.getTodoById(todoId);
            if (todo == null) {
                throw new CustomErrorException("Todo does not exist");
            }
            todoService.deleteTodo(todoId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }*/

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long todoId) {
        try {
            Todo todo = todoService.getTodoById(todoId);
            if (todo == null) {
                throw new CustomErrorException("Todo does not exist");
            }
            todoService.deleteTodo(todoId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}
