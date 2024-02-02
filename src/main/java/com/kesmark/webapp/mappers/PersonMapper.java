package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.AddressRequestDTO;
import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.entities.Address;
import com.kesmark.webapp.models.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {

  private final AddressMapper addressMapper;

  @Autowired
  public PersonMapper(AddressMapper addressMapper, ContactMapper contactMapper) {
    this.addressMapper = addressMapper;
  }

  public Person mapToEntity(PersonRequestDTO personRequestDTO, Person person) {

    person.setFirstName(personRequestDTO.getFirstName());
    person.setMiddleName(personRequestDTO.getMiddleName());
    person.setFamilyName(personRequestDTO.getFamilyName());
    person.setAddressList(mappAddresListRequestDTO(personRequestDTO, person));

    return person;
  }

  private List<Address> mappAddresListRequestDTO(PersonRequestDTO personRequestDTO, Person person) {
    List<Address> addressList = new ArrayList<>();
    for (AddressRequestDTO addressRequestDTO : personRequestDTO.getAddressList()) {

      addressList.add(addressMapper.mapToEntity(addressRequestDTO, person));
    }
    return addressList;
  }

  public PersonResponseDTO mapToResponseDTO(Person person) {
    PersonResponseDTO personResponseDTO = new PersonResponseDTO();
    personResponseDTO.setId(person.getId());
    personResponseDTO.setFirstName(person.getFirstName());
    personResponseDTO.setMiddleName(person.getMiddleName());
    personResponseDTO.setFamilyName(person.getFamilyName());
    personResponseDTO.setAddressList(person.getAddressList());

    return personResponseDTO;
  }

}
