package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
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

    person.setPermanentAddress(addressMapper.mapToEntity(personRequestDTO.getPermanentAddress(),person));
    if (personRequestDTO.getTemporaryAddress() != null) {
      person.setTemporaryAddress(addressMapper.mapToEntity(personRequestDTO.getTemporaryAddress(),person));
    }

    return person;
  }

  public PersonResponseDTO mapToResponseDTO(Person person) {
    PersonResponseDTO personResponseDTO = new PersonResponseDTO();
    personResponseDTO.setId(person.getId());
    personResponseDTO.setFirstName(person.getFirstName());
    personResponseDTO.setMiddleName(person.getMiddleName());
    personResponseDTO.setFamilyName(person.getFamilyName());

    personResponseDTO.setPermanentAddress(addressMapper.mapToResponseDTO(person.getPermanentAddress()));
    if (person.getTemporaryAddress() != null) {
      personResponseDTO.setTemporaryAddress(addressMapper.mapToResponseDTO(person.getTemporaryAddress()));
    }
    return personResponseDTO;
  }



}
