package com.kesmark.webapp.repositories;

import com.kesmark.webapp.models.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
