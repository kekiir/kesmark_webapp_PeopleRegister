package com.kesmark.webapp.models.DTOs.errorDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {

  private String status;
  private String message;

  public ErrorDTO(String message) {
    this.status = "error";
    this.message = message;
  }

}
