package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Area;
import ad.uda.tprats.workitdata.entities.Todo;
import ad.uda.tprats.workitdata.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

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

    // DELETE
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    // UPDATE
    public Todo updateTodo(Long todoId, Todo details) {
        Todo todo = todoRepository.findById(todoId).get();
        todo.setTitle(details.getTitle());
        todo.setChecked(details.isChecked());
        todo.setCreationDateTime(details.getCreationDateTime());
        todo.setUser(details.getUser());
        return todoRepository.save(todo);
    }
}
