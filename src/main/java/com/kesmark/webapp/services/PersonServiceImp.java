package com.kesmark.webapp.services;

import com.kesmark.webapp.mappers.*;
import com.kesmark.webapp.models.DTOs.requestDTOs.*;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.entities.*;
import com.kesmark.webapp.models.exceptions.*;
import com.kesmark.webapp.models.enums.AddressType;
import com.kesmark.webapp.models.enums.ContactType;
import com.kesmark.webapp.repositories.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersoneService {

  private PersonRepository personRepository;
  private AddressRepository addressRepository;
  public ContactRepository contactRepository;
  private PersonMapper personMapper;
  private AddressMapper addressMapper;
  private ContactMapper contactMapper;

  @Override
  public PersonResponseDTO createPerson(PersonRequestDTO personRequestDTO) {
    checkCorrectAddresType(personRequestDTO);
    checkContacTypes(personRequestDTO);

    Person newPerson = new Person();
    newPerson.setFirstName(personRequestDTO.getFirstName());
    newPerson.setMiddleName(personRequestDTO.getMiddleName());
    newPerson.setFamilyName(personRequestDTO.getFamilyName());
    personRepository.save(newPerson);

    saveAddreses(newPerson, personRequestDTO);

    return personMapper.mapToResponseDTO(personRepository.save(newPerson));
  }

  private void saveAddreses(Person newPerson, PersonRequestDTO personRequestDTO) {
    Address newAddress0 = addressMapper.mapToEntity(personRequestDTO.getAddressList().get(0), newPerson);
    Address newAddress1 = addressMapper.mapToEntity(personRequestDTO.getAddressList().get(1), newPerson);

    addressRepository.save(newAddress1);
    addressRepository.save(newAddress0);
    saveContacts(newAddress0, newAddress1);
    newPerson.getAddressList().add(newAddress0);
    newPerson.getAddressList().add(newAddress1);
  }

  private void saveContacts(Address newAddress0, Address newAddress1) {
    for (Contact contact : newAddress0.getContactList()) {
      contact.setAddress(newAddress0);
      contactRepository.save(contact);
    }
    for (Contact contact : newAddress1.getContactList()) {
      contact.setAddress(newAddress1);
      contactRepository.save(contact);
    }

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

    Person updatedPerson = personRepository.save(person);

    return personMapper.mapToResponseDTO(updatedPerson);
  }

  private void updatePersonFromRequest(Person person, PersonRequestDTO personRequestDTO) {
    person.setFirstName(personRequestDTO.getFirstName());
    person.setMiddleName(personRequestDTO.getMiddleName());
    person.setFamilyName(personRequestDTO.getFamilyName());

    for (int i = 0; i < 2; i++) {
      if (personRequestDTO.getAddressList().get(i) != null) {
        updateAddress(person.getAddressList().get(i), personRequestDTO.getAddressList().get(i));
      }
      List<Contact> updatedContactList = updateContactList(personRequestDTO, person, i);

      person.getAddressList().get(i).setContactList(updatedContactList);

      addressRepository.save(person.getAddressList().get(0));
    }

    personRepository.save(person);
  }

  private List<Contact> updateContactList(PersonRequestDTO personRequestDTO, Person person, int i) {

    return personRequestDTO.getAddressList().get(i).getContactList()
      .stream()
      .map(c -> contactMapper.mapToEntity(c, person.getAddressList().get(i)))
      .collect(Collectors.toList());
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
