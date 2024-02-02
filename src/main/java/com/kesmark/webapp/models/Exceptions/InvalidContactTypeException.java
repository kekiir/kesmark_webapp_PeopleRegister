package com.kesmark.webapp.models.Exceptions;

public class InvalidContactTypeException extends RuntimeException {

  public static final String MESSAGE = "Invalid usertype.";

  public InvalidContactTypeException() {
    super(MESSAGE);
  }

}
