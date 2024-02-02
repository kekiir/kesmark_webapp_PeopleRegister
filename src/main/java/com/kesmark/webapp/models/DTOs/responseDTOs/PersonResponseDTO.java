package com.kesmark.webapp.models.DTOs.responseDTOs;

import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import com.kesmark.webapp.models.entities.Address;
import com.kesmark.webapp.models.enums.AddressType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

  private Integer id;
  private String firstName;
  private String middleName;
  private String familyName;
  private List<Address> addressList;

}
