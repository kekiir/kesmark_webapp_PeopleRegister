package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

  private final AddressMapper addressMapper;
  private final ContactMapper contactMapper;

  @Autowired
  public PersonMapper(AddressMapper addressMapper, ContactMapper contactMapper) {
    this.addressMapper = addressMapper;
    this.contactMapper = contactMapper;
  }

  public Person mapToEntity(PersonRequestDTO personRequestDTO) {
    Person person = new Person();
    person.setFirstName(personRequestDTO.getFirstName());
    person.setMiddleName(personRequestDTO.getMiddleName());
    person.setFamilyName(personRequestDTO.getFamilyName());

    // Mapping permanent address
    person.setPermanentAddress(addressMapper.mapToEntity(personRequestDTO.getPermanentAddress()));

    // Mapping temporary address, if it exists
    if (personRequestDTO.getTemporaryAddress() != null) {
      person.setTemporaryAddress(addressMapper.mapToEntity(personRequestDTO.getTemporaryAddress()));
    }

    return person;
  }
}
