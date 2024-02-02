package com.kesmark.webapp.models.exceptions;

public class InvalidAddresTypeException extends RuntimeException {

  public static final String MESSAGE = "Invalid addres type. Valid types: PERMANENT or TEMPORARY";

  public InvalidAddresTypeException() {
    super(MESSAGE);
  }

}
