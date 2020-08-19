package com.todo.list;

import com.todo.list.model.Task;
import com.todo.list.model.Todo;
import com.todo.list.repository.TaskRepository;
import com.todo.list.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApplication implements CommandLineRunner {

  @Autowired
  private TodoRepository todoRepository;

  public static void main(String[] args) {
    SpringApplication.run(ToDoListApplication.class, args);
  }

  @Override
  public void run(String... args) {
    todoRepository.save(
        new Todo("Grocery",
            new Task("Milk", "yes"),
            new Task("Pepsi", "no"),
            new Task("Cheese", "yes")
        ));

    todoRepository.save(
        new Todo("Interview Prep",
            new Task("Resume", "yes"),
            new Task("Leetcode", "yes"),
            new Task("Coding Assignment", "no")
        ));
  }
}
