package com.kesmark.webapp.models.DTOs;

import com.kesmark.webapp.models.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDTO {

  @NotNull(message = "contactType: Contact type is required.")
  private ContactType contactType;

  @NotNull(message = "contact: Contact type is required.")
  private ContactType contact;
}
