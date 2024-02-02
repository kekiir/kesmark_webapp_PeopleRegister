package com.kesmark.webapp.controllers;

import com.kesmark.webapp.models.DTOs.errorDTOs.ErrorDTO;
import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.exceptions.InvalidAddresTypeException;
import com.kesmark.webapp.models.exceptions.InvalidContactTypeException;
import com.kesmark.webapp.services.PersoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping( produces = "application/json")
public class PersonController {

  private PersoneService personService;

  @PostMapping(path = "/person")
  public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {

    try {
      PersonResponseDTO personResponseDTO = personService.createPerson(personRequestDTO);
      return ResponseEntity.ok().body(personResponseDTO);
    } catch (IllegalArgumentException | InvalidAddresTypeException |
             InvalidContactTypeException e) {
      return ResponseEntity.status(406).body(new ErrorDTO(e.getMessage()));
    }
  }

}
