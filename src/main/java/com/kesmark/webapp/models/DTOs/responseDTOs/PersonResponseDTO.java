package com.kesmark.webapp.models.DTOs.responseDTOs;
import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponseDTO {

  private Integer id;
  private String firstName;
  private String middleName;
  private String familyName;
  private AddressRequestDTO permanentAddress;
  private AddressRequestDTO temporaryAddress;

}
