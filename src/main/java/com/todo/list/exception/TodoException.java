package com.todo.list.exception;

public class TodoException extends RuntimeException {

  private static final long serialVersionUID = -1679807925070997937L;

  public TodoException() {
    super();
  }

  public TodoException(String message) {
    super(message);
  }

  public TodoException(String message, Throwable cause) {
    super(message, cause);
  }

  public TodoException(Throwable cause) {
    super(cause);
  }

  protected TodoException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
