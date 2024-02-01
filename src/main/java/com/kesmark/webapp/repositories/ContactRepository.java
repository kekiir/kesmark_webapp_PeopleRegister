package com.kesmark.webapp.repositories;

import com.kesmark.webapp.models.entities.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
}
