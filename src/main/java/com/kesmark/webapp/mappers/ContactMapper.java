package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.ContactRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.ContactResponseDTO;
import com.kesmark.webapp.models.entities.Address;
import com.kesmark.webapp.models.entities.Contact;
import com.kesmark.webapp.models.enums.ContactType;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public Contact mapToEntity(ContactRequestDTO contactRequestDTO, Address address) {
    Contact contact = new Contact();
    contact.setAddress(address);
    contact.setContactType(ContactType.valueOf(contactRequestDTO.getContactType().toString()));
    contact.setContact(contactRequestDTO.getContact());
    return contact;
  }
  public ContactResponseDTO mapToResponseDTO(Contact contact) {
    ContactResponseDTO contactResponseDTO = new ContactResponseDTO();
    contactResponseDTO.setId(contact.getId());
    contactResponseDTO.setContactType(contact.getContactType());
    contactResponseDTO.setContact(contact.getContact());
    return contactResponseDTO;
  }




}
