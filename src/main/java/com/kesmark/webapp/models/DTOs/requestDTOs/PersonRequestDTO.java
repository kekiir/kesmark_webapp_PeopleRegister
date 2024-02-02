package com.kesmark.webapp.models.DTOs.requestDTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequestDTO {

  @NotBlank(message = "firstName: First name is required.")
  private String firstName;
  @NotNull(message = "middleName: Middle name can not be null.")
  private String middleName;

  @NotBlank(message = "familyName: Family name is required.")
  private String familyName;

  @Valid
  @NotNull(message = "permanentAddress: Permanent address is required.")
  private AddressRequestDTO permanentAddress;

  @Valid
  private AddressRequestDTO temporaryAddress;
}
