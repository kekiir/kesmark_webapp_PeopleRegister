package com.kesmark.webapp.models.DTOs.requestDTOs;

import com.kesmark.webapp.models.enums.AddressType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDTO {

  @NotBlank(message = "line1: Line 1 is required.")
  private String line1;
  @NotBlank(message = "line1: Line 2 is required.")
  private String line2;
  @NotBlank(message = "line1: Line 3 is required.")
  private String line3;

  @NotBlank(message = "city: City is required.")
  private String city;

  @NotBlank(message = "countryProvince: Country/Province is required.")
  private String countryProvince;

  @NotNull(message = "zipOrPostcode: Zip or postcode is required.")
  @Min(message = "Zip code can not be negative", value = 0L)
  private Integer zipOrPostcode;

  @NotBlank(message = "country: Country is required.")
  private String country;

  @NotNull(message = "addressType: Address type is required.")
  private String addressType;

  @Valid
  private List<ContactRequestDTO> contactList;
}