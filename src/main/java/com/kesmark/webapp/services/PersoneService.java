package com.kesmark.webapp.services;

import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;

public interface PersoneService {

  PersonResponseDTO createPerson( PersonRequestDTO personRequestDTO);
}
