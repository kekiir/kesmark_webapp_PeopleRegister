package com.kesmark.webapp.services;

import com.kesmark.webapp.mappers.AddressMapper;
import com.kesmark.webapp.mappers.PersonMapper;
import com.kesmark.webapp.models.DTOs.requestDTOs.*;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.entities.*;
import com.kesmark.webapp.models.exceptions.*;
import com.kesmark.webapp.models.enums.AddressType;
import com.kesmark.webapp.models.enums.ContactType;
import com.kesmark.webapp.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersoneService {

  private PersonRepository personRepository;
  private AddressRepository addressRepository;
  public ContactRepository contactRepository;
  private PersonMapper personMapper;
  private AddressMapper addressMapper;

  @Override
  public PersonResponseDTO createPerson(PersonRequestDTO personRequestDTO) {
    checkCorrectAddresType(personRequestDTO);
    checkContacTypes(personRequestDTO);

    Person newPerson = new Person();
    newPerson.setFirstName(personRequestDTO.getFirstName());
    newPerson.setMiddleName(personRequestDTO.getMiddleName());
    newPerson.setFamilyName(personRequestDTO.getFamilyName());
    personRepository.save(newPerson);

    Address newAddress0 = addressMapper.mapToEntity(personRequestDTO.getAddressList().get(0), newPerson);
    Address newAddress1 = addressMapper.mapToEntity(personRequestDTO.getAddressList().get(1), newPerson);
    addressRepository.save(newAddress1);
    addressRepository.save(newAddress0);
    newPerson.getAddressList().add(newAddress0);
    newPerson.getAddressList().add(newAddress1);


    return personMapper.mapToResponseDTO(personRepository.save(newPerson));
  }

  @Override
  public Object findPersonByID(Integer id) throws IdNotFoundException {

    Optional<Person> person = personRepository.findById(id);
    if (person.isEmpty())
      throw new IdNotFoundException();

    return personMapper.mapToResponseDTO(person.get());
  }

  @Override
  public Object updatePersonByID(Integer id, PersonRequestDTO personRequestDTO) {
    Optional<Person> optionalPerson = personRepository.findById(id);
    if (optionalPerson.isEmpty()) {
      throw new IdNotFoundException();
    }

    Person person = optionalPerson.get();
    updatePersonFromRequest(person, personRequestDTO);

    // Save the updated person entity
    Person updatedPerson = personRepository.save(person);

    return personMapper.mapToResponseDTO(updatedPerson);
  }

  private void updatePersonFromRequest(Person person, PersonRequestDTO personRequestDTO) {
    person.setFirstName(personRequestDTO.getFirstName());
    person.setMiddleName(personRequestDTO.getMiddleName());
    person.setFamilyName(personRequestDTO.getFamilyName());

    for (int i = 0; i < 2; i++) {
      updateAddress(person.getAddressList().get(i), personRequestDTO.getAddressList().get(i));
    }

    personRepository.save(person);
  }

  private void updateAddress(Address address, AddressRequestDTO addressRequestDTO) {
    if (addressRequestDTO != null) {
      address.setLine_1(addressRequestDTO.getLine1());
      address.setLine_2(addressRequestDTO.getLine2());
      address.setLine_3(addressRequestDTO.getLine3());
      address.setCity(addressRequestDTO.getCity());
      address.setCountryProvince(addressRequestDTO.getCountryProvince());
      address.setZipOrPostcode(addressRequestDTO.getZipOrPostcode());
      address.setAddressType(AddressType.valueOf(addressRequestDTO.getAddressType()));
      address.setCountry(addressRequestDTO.getCountry());
    }
  }

  public void checkCorrectAddresType(PersonRequestDTO personRequestDTO) throws InvalidAddresTypeException {
    try {

      for (AddressRequestDTO addressRequestDTO : personRequestDTO.getAddressList()) {
        AddressType.valueOf(addressRequestDTO.getAddressType());
      }
    } catch (IllegalArgumentException e) {
      throw new InvalidAddresTypeException();
    }
  }

  public void checkContacTypes(PersonRequestDTO personRequestDTO) throws InvalidContactTypeException {

    for (AddressRequestDTO addressRequestDTO : personRequestDTO.getAddressList()) {
      for (ContactRequestDTO contactRequestDTO : addressRequestDTO.getContactList()) {
        checkCorrectContactType(contactRequestDTO.getContactType());
      }

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
