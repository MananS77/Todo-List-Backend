package com.todo.list.controller;

import com.todo.list.model.Task;
import com.todo.list.model.Todo;
import com.todo.list.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class TodoController {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  TodoService todoService;

  TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/todo")
  public Iterable<Todo> getAllTodos() {
    return todoService.getTodoList();
  }

  @GetMapping("/todo/{id}")
  public Todo getTodoById(@PathVariable long id) {
    return todoService.getTodo(id);
  }

  @GetMapping("/todo/{id}/tasks")
  public Iterable<Task> getTasksUnderTodo(@PathVariable long id) {
    return todoService.getTasksUnderTodo(id);
  }

  @PutMapping("/todo")
  public ResponseEntity<String> addTodo(@RequestBody Todo newTodo) {
    try {
      todoService.addTodo(newTodo);
      return new ResponseEntity<String>("Todo successfully created", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unable to add new todo", e);
    }
    return new ResponseEntity<String>(
        "Todo couldn't be added. Check if Todo with same name already exists",
        HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/todo")
  public ResponseEntity<String> updateTodo(@RequestBody Todo updatedTodo) {
    try {
      todoService.updateTodo(updatedTodo);
      return new ResponseEntity<String>("Todo successfully updated", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unable to update todo with id " + updatedTodo.getTodoId(), e);
    }
    return new ResponseEntity<String>("Todo couldn't be updated", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/todo/{id}")
  public ResponseEntity<String> deleteTodo(@PathVariable long id) {
    try {
      todoService.deleteTodo(id);
      return new ResponseEntity<String>("Todo successfully deleted", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unable to delete todo with id " + id, e);
    }
    return new ResponseEntity<String>("Todo couldn't be deleted", HttpStatus.BAD_REQUEST);
  }
}
