package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.AddressResponseDTO;
import com.kesmark.webapp.models.entities.Address;
import com.kesmark.webapp.models.entities.Person;
import com.kesmark.webapp.models.enums.AddressType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AddressMapper {

  private final ContactMapper contactMapper;

  public Address mapToEntity(AddressRequestDTO addressRequestDTO, Person person) {
    Address address = new Address();
    address.setLine_1(addressRequestDTO.getLine1());
    address.setLine_2(addressRequestDTO.getLine2());
    address.setLine_3(addressRequestDTO.getLine3());
    address.setCity(addressRequestDTO.getCity());
    address.setCountryProvince(addressRequestDTO.getCountryProvince());
    address.setZipOrPostcode(addressRequestDTO.getZipOrPostcode());
    address.setCountry(addressRequestDTO.getCountry());
    address.setAddressType(AddressType.valueOf(addressRequestDTO.getAddressType()));
    if (addressRequestDTO.getContactList() != null) {
      address.setContactList(addressRequestDTO.getContactList().stream()
        .map(c -> contactMapper.mapToEntity(c,address))
        .collect(Collectors.toList()));
    }
    address.setPerson(person);
    return address;
  }

    public AddressResponseDTO mapToResponseDTO(Address address) {
      AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
      addressResponseDTO.setId(address.getId());
      addressResponseDTO.setLine1(address.getLine_1());
      addressResponseDTO.setLine2(address.getLine_2());
      addressResponseDTO.setLine3(address.getLine_3());
      addressResponseDTO.setCity(address.getCity());
      addressResponseDTO.setCountryProvince(address.getCountryProvince());
      addressResponseDTO.setZipOrPostcode(address.getZipOrPostcode());
      addressResponseDTO.setCountry(address.getCountry());
      addressResponseDTO.setAddressType(address.getAddressType());
      // Map contactList if it exists
      if (address.getContactList() != null) {
        addressResponseDTO.setContactList(address.getContactList().stream()
          .map(contactMapper::mapToResponseDTO)
          .collect(Collectors.toList()));
      }
      return addressResponseDTO;


  }
}
