package com.kesmark.webapp.services;

import com.kesmark.webapp.mappers.PersonMapper;
import com.kesmark.webapp.models.DTOs.requestDTOs.ContactRequestDTO;
import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.exceptions.*;
import com.kesmark.webapp.models.entities.Person;
import com.kesmark.webapp.models.enums.AddressType;
import com.kesmark.webapp.models.enums.ContactType;
import com.kesmark.webapp.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersoneService {

  private PersonRepository personRepository;
  private PersonMapper personMapper;

  @Override
  public PersonResponseDTO createPerson(PersonRequestDTO personRequestDTO) {
    checkCorrectAddresType(personRequestDTO.getPermanentAddress().getAddressType().toString());
    checkContacTypes(personRequestDTO);
    if (personRequestDTO.getTemporaryAddress() != null) {
      checkCorrectAddresType(personRequestDTO.getTemporaryAddress().getAddressType().toString());
    }

    Person newPerson = setPersonVariables (personRequestDTO);

    return personMapper.mapToResponseDTO(personRepository.save(newPerson));
  }

  @Override
  public Object findPersonByID(Integer id) throws IdNotFoundException {

    Optional <Person> person = personRepository.findById(id);
    if (person.isEmpty()) throw new IdNotFoundException();

    return personMapper.mapToResponseDTO(person.get());
  }

  private Person setPersonVariables(PersonRequestDTO personRequestDTO) {


return personMapper.mapToEntity(personRequestDTO);
  }

  public void checkCorrectAddresType(String addresType) throws InvalidAddresTypeException {
    try {
      AddressType.valueOf(addresType);
    } catch (IllegalArgumentException e) {
      throw new InvalidAddresTypeException();
    }
  }

  public void checkContacTypes(PersonRequestDTO personRequestDTO) throws InvalidContactTypeException {
    for (ContactRequestDTO contact : personRequestDTO.getPermanentAddress().getContactList()) {
      checkCorrectContactType(contact.getContactType().toString());
    }

    for (ContactRequestDTO contact : personRequestDTO.getTemporaryAddress().getContactList()) {
      checkCorrectContactType(contact.getContactType().toString());
    }
  }

  public void checkCorrectContactType(String contactType) throws InvalidContactTypeException {
    try {
      ContactType.valueOf(contactType);
    } catch (IllegalArgumentException e) {
      throw new InvalidContactTypeException();
    }
  }

}
