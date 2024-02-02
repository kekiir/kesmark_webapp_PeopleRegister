package com.kesmark.webapp.mappers;

import com.kesmark.webapp.models.DTOs.requestDTOs.ContactRequestDTO;
import com.kesmark.webapp.models.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public Contact mapToEntity(ContactRequestDTO contactRequestDTO) {
    Contact contact = new Contact();
    contact.setContactType(contactRequestDTO.getContactType());
    contact.setContact(contactRequestDTO.getContact().toString());
    return contact;
  }
}
