package com.kesmark.webapp.models.Exceptions;

public class InvalidAddresTypeException extends RuntimeException {

  public static final String MESSAGE = "Invalid energy source.";

  public InvalidAddresTypeException() {
    super(MESSAGE);
  }

}
