package com.kesmark.webapp.models.DTOs.requestDTOs;

import com.kesmark.webapp.models.enums.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequestDTO {

  @NotNull(message = "contactType: Contact type is required.")
  private ContactType contactType;

  @NotNull(message = "contact: Contact type is required.")
  private ContactType contact;
}
