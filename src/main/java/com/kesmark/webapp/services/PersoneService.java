package com.kesmark.webapp.services;

import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;

public interface PersoneService {

  PersonResponseDTO createPerson( PersonRequestDTO personRequestDTO);

  Object findPersonByID(Integer id);

  Object updatePersonByID(Integer id, PersonRequestDTO personRequestDTO);

  void deletePersoneById(Integer id);

}
