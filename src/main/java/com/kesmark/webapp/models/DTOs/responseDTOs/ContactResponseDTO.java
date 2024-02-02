package com.kesmark.webapp.models.DTOs.responseDTOs;

import com.kesmark.webapp.models.enums.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO {

  private Integer id;
  private ContactType contactType;
  private String contact;

}
