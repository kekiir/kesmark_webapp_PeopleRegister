package com.kesmark.webapp.models.exceptions;

public class InvalidContactTypeException extends RuntimeException {

  public static final String MESSAGE = "Invalid contact type. Valid types: PHONE, EMAIL, OTHER";

  public InvalidContactTypeException() {
    super(MESSAGE);
  }

}
