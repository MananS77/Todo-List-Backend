package com.todo.list.service;

import com.todo.list.exception.TaskException;
import com.todo.list.exception.TodoException;
import com.todo.list.model.Task;
import com.todo.list.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

  TaskRepository taskRepository;

  TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Iterable<Task> getTaskList() {
    return taskRepository.findAll();
  }

  public Task getTask(long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new TaskException("Task with " + id + " not found"));
  }

  public void addTask(Task newTask) {
    try {
      Iterable<Task> listOfAllTasks = getTaskList();
      for (Task listOfAllTask : listOfAllTasks) {
        if (newTask.equals(listOfAllTasks)) {
          return;
        }
      }
      taskRepository.save(newTask);
    } catch (Exception e) {
      throw new TodoException("Malformed Task object passed");
    }
  }

  public void updateTodo(long id, String newTodoName, String newStatus) {
    Task task = getTask(id);
    if (task != null && !task.getTaskName().isEmpty()) {
      task.setTaskName(newTodoName);
      task.setDoneStatus(newStatus);
      taskRepository.save(task);
    } else {
      throw new TodoException("Task with " + id + " not found");
    }
  }

  public void deleteTodo(long id) {
    Task task = getTask(id);
    if (task != null && !task.getTaskName().isEmpty()) {
      taskRepository.delete(task);
    } else {
      throw new TodoException("Task with " + id + " not found");
    }
  }
}
