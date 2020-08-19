package com.todo.list.repository;

import com.todo.list.model.Task;
import com.todo.list.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}

