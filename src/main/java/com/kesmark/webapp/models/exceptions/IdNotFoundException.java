package com.kesmark.webapp.models.exceptions;

public class IdNotFoundException extends RuntimeException {

  public static final String MESSAGE = "Id not found";

  public IdNotFoundException() {
    super(MESSAGE);
  }

}

