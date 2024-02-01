package com.kesmark.webapp.repositories;

import com.kesmark.webapp.models.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
