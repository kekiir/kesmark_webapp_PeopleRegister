package com.kesmark.webapp.models.DTOs.responseDTOs;
import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {

  private Integer id;
  private String firstName;
  private String middleName;
  private String familyName;
  private AddressResponseDTO permanentAddress;
  private AddressResponseDTO temporaryAddress;

}
