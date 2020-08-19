package com.todo.list.controller;

import com.todo.list.model.Task;
import com.todo.list.service.TaskService;
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
public class TaskController {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/todo/task")
  public Iterable<Task> getAllTasks() {
    return taskService.getTaskList();
  }

  @GetMapping("/todo/task/{id}")
  public Task getTaskById(@PathVariable long id) {
    return taskService.getTask(id);
  }

  @PutMapping("/todo/task")
  public ResponseEntity<String> addTask(@RequestBody Task newTask) {
    try {
      taskService.addTask(newTask);
      return new ResponseEntity<String>("Task added successfully", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unable to add task to To Do list", e);
    }
    return new ResponseEntity<String>("Task couldn't be added. Check if task already exists",
        HttpStatus.BAD_REQUEST);
  }


  @PostMapping("/todo/task/{id}")
  public ResponseEntity<String> updateTask(@PathVariable long id,
      @RequestParam String newTaskName, @RequestParam String newStatus) {
    try {
      taskService.updateTodo(id, newTaskName, newStatus);
      return new ResponseEntity<>("Task successfully updated", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unable to update task", e);
    }
    return new ResponseEntity<>("Task couldn't be updated", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/todo/task/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable long id) {
    try {
      taskService.deleteTodo(id);
      return new ResponseEntity<String>("Task deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Task does not exist. So can't perform deletion");
    }
    return new ResponseEntity<String>("Task doesn't exist", HttpStatus.NOT_FOUND);
  }

}
