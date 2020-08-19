package com.todo.list.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(exclude = "taskSet")
@Entity
@NoArgsConstructor
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long todoId;

  @NonNull
  @Column(unique = true)
  private String todoName;

  @OneToMany(mappedBy = "taskTodo", cascade = CascadeType.ALL)
  @JsonBackReference
  @Getter
  private Set<Task> taskSet = new HashSet<Task>();

  public Todo(String todoName, Task... taskSet) {
    this.todoName = todoName;
    this.taskSet = Stream.of(taskSet).collect(Collectors.toSet());
    this.taskSet.forEach(x -> x.setTaskTodo(this));
  }
}
