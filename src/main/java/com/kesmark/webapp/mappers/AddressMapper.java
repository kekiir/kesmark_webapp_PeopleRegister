package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import com.kesmark.webapp.models.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

  public Address mapToEntity(AddressRequestDTO addressRequestDTO) {
    Address address = new Address();
    address.setLine_1(addressRequestDTO.getLine1());
    address.setLine_2(addressRequestDTO.getLine2());
    address.setLine_3(addressRequestDTO.getLine3());
    address.setCity(addressRequestDTO.getCity());
    address.setCountryProvince(addressRequestDTO.getCountryProvince());
    address.setZipOrPostcode(addressRequestDTO.getZipOrPostcode());
    address.setCountry(addressRequestDTO.getCountry());
    address.setAddressType(addressRequestDTO.getAddressType());
    // You need to map contactList too if it exists
    // address.setContactList(contactListMapper.mapToEntities(addressRequestDTO.getContactList()));
    return address;
  }
}
