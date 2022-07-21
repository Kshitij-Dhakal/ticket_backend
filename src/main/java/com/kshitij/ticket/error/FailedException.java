package com.kshitij.ticket.error;

public class FailedException extends RuntimeException {
  public FailedException(String message) {
    super(message);
  }
}
