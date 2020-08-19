package com.todo.list.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private long taskId;

  @NonNull
  @Column(unique = true)
  @Getter
  @Setter
  private String taskName;

  @ManyToOne
  @JoinColumn
  @Setter
  @JsonManagedReference
  private Todo taskTodo;

  @NonNull
  @Column
  @Getter
  @Setter
  private String doneStatus;

  public Task(String taskName, String doneStatus) {
    this.taskName = taskName;
    this.doneStatus = doneStatus;
  }
}
