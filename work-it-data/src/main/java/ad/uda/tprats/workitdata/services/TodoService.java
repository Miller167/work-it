package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.dtos.TodoDTO;
import ad.uda.tprats.workitdata.entities.Todo;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.TodoRepository;
import ad.uda.tprats.workitdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public Todo createTodo(Todo todo) {

        return todoRepository.save(todo);
    }

    // READ
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // READ
    public Todo getTodoById(Long todoId) {
        return todoRepository.getById(todoId);
    }


    public List<Todo> getTodosByUser(User user) {
        return todoRepository.getTodosByUser(user);
    }
    // DELETE
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    // UPDATE
    public Todo updateTodo(Long todoId, TodoDTO details) {
        User user = userRepository.getById(details.getUser());
        Todo todo = todoRepository.findById(todoId).get();
        todo.setTitle(details.getTitle());
        todo.setChecked(details.isChecked());
        todo.setCreationDateTime(details.getCreationDateTime());
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo setTodoFromTodoDTO(TodoDTO details) {
        User user = userRepository.getById(details.getUser());
        Todo todo = new Todo();
        todo.setTitle(details.getTitle());
        todo.setChecked(details.isChecked());
        todo.setCreationDateTime(details.getCreationDateTime());
        todo.setUser(user);
        return todoRepository.save(todo);
    }
}
