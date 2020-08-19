package com.todo.list.service;

import com.todo.list.exception.TodoException;
import com.todo.list.model.Task;
import com.todo.list.model.Todo;
import com.todo.list.repository.TodoRepository;
import java.util.Collections;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService {

  TodoRepository todoRepository;

  TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Iterable<Todo> getTodoList() {
    return todoRepository.findAll();
  }

  public Todo getTodo(long id) {
    return todoRepository.findById(id)
        .orElseThrow(() -> new TodoException("Todo with id " + id + " not found"));
  }

  public void addTodo(Todo newTodo) {
    try {
      Iterable<Todo> listOfAllTodos = getTodoList();
      for (Todo listOfAllTodo : listOfAllTodos) {
        if (newTodo.equals(listOfAllTodo)) {
          return;
        }
      }
      todoRepository.save(newTodo);
    } catch (Exception e) {
      throw new TodoException("Malformed Todo object passed");
    }
  }

  public void updateTodo(Todo updatedTodo) {
    Todo todo = getTodo(updatedTodo.getTodoId());
    if (todo != null && !todo.getTodoName().isEmpty()) {
      todoRepository.save(updatedTodo);
    } else {
      throw new TodoException("Todo with id " + updatedTodo.getTodoId() + " not found");
    }
  }

  public void deleteTodo(long id) {
    Todo todo = getTodo(id);
    if (todo != null && !todo.getTodoName().isEmpty()) {
      todoRepository.delete(todo);
    } else {
      throw new TodoException("Todo with id " + id + " not found");
    }
  }

  public Set<Task> getTasksUnderTodo(long id) {
    try {
      Todo todo = getTodo(id);
      return todo != null ? todo.getTaskSet() : Collections.emptySet();
    } catch (Exception e) {
      throw new TodoException("Exception while getting tasks under Todo with id " + id, e);
    }
  }
}
