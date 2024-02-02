package com.kesmark.webapp.services;

import com.kesmark.webapp.mappers.PersonMapper;
import com.kesmark.webapp.models.DTOs.requestDTOs.*;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.entities.*;
import com.kesmark.webapp.models.exceptions.*;
import com.kesmark.webapp.models.enums.AddressType;
import com.kesmark.webapp.models.enums.ContactType;
import com.kesmark.webapp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImp implements PersoneService {

  private PersonRepository personRepository;
  private AddressRepository addressRepository;
  private ContactRepository contactRepository;
  private PersonMapper personMapper;

  @Override
  public PersonResponseDTO createPerson(PersonRequestDTO personRequestDTO) {
    checkCorrectAddresType(personRequestDTO.getPermanentAddress().getAddressType().toString());
    checkContacTypes(personRequestDTO);
    if (personRequestDTO.getTemporaryAddress() != null) {
      checkCorrectAddresType(personRequestDTO.getTemporaryAddress().getAddressType().toString());
    }

    Person newPerson = personMapper.mapToEntity(personRequestDTO);

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
    Optional<Person> person = personRepository.findById(id);
    if (person.isEmpty())
      throw new IdNotFoundException();
    updatePersonFromRequest(person.get(), personRequestDTO);
    personRepository.save(person.get());

    return personMapper.mapToResponseDTO(personRepository.save(person.get()));
  }

  public static void updatePersonFromRequest(Person person, PersonRequestDTO personRequestDTO) {
    person.setFirstName(personRequestDTO.getFirstName());
    person.setMiddleName(personRequestDTO.getMiddleName());
    person.setFamilyName(personRequestDTO.getFamilyName());

    updateAddressFromRequest(person.getPermanentAddress(), personRequestDTO.getPermanentAddress());
    if (personRequestDTO.getTemporaryAddress() != null) {
      updateAddressFromRequest(person.getTemporaryAddress(), personRequestDTO.getTemporaryAddress());
    }

  }

  public static void updateAddressFromRequest(Address address, AddressRequestDTO addressRequestDTO) {
    address.setLine_1(addressRequestDTO.getLine1());
    address.setLine_2(addressRequestDTO.getLine2());
    address.setLine_3(addressRequestDTO.getLine3());
    address.setCity(addressRequestDTO.getCity());
    address.setCountryProvince(addressRequestDTO.getCountryProvince());
    address.setZipOrPostcode(addressRequestDTO.getZipOrPostcode());
    address.setCountry(addressRequestDTO.getCountry());
    address.setAddressType(AddressType.valueOf(addressRequestDTO.getAddressType()));

    // Update contact list if it's not null
    if (addressRequestDTO.getContactList() != null) {
      List<Contact> existingContacts = address.getContactList();
      List<ContactRequestDTO> updatedContactDTOs = addressRequestDTO.getContactList();

      for (int i = 0; i < Math.min(existingContacts.size(), updatedContactDTOs.size()); i++) {
        Contact existingContact = existingContacts.get(i);
        ContactRequestDTO updatedContactDTO = updatedContactDTOs.get(i);

        // Update properties of existing contact with corresponding values from DTO
        existingContact.setContactType(ContactType.valueOf(updatedContactDTO.getContactType()));
        existingContact.setContact(updatedContactDTO.getContact());
        // Set other properties as needed

      }
    }
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
