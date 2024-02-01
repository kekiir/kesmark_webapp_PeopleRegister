package com.kesmark.webapp.models.DTOs.errorDTOs;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorListResponseDTO {
  private String status = "error";
  private List<String> message;

  public void setStatusError() {
    this.setStatus("error");
  }

}
