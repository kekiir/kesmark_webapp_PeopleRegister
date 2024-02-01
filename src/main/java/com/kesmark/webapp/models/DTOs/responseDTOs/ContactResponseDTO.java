package com.kesmark.webapp.models.DTOs.responseDTOs;

import com.kesmark.webapp.models.enums.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactResponseDTO {

  private Integer id;
  private ContactType contactType;
  private ContactType contact;

}
