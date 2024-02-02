package com.kesmark.webapp.models.DTOs.responseDTOs;

import com.kesmark.webapp.models.DTOs.requestDTOs.ContactRequestDTO;
import com.kesmark.webapp.models.enums.AddressType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {

  private Integer id;
  private String line1;
  private String line2;
  private String line3;
  private String city;
  private String countryProvince;
  private Integer zipOrPostcode;
  private String country;
  private AddressType addressType;
  private List<ContactResponseDTO> contactList;

}
