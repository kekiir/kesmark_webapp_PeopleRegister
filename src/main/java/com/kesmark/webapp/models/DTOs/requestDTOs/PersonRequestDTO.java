package com.kesmark.webapp.models.DTOs.requestDTOs;

import com.kesmark.webapp.models.entities.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

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
  private List<AddressRequestDTO> addressList;
}