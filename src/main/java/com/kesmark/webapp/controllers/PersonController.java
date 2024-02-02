package com.kesmark.webapp.controllers;

import com.kesmark.webapp.models.DTOs.errorDTOs.ErrorDTO;
import com.kesmark.webapp.models.DTOs.requestDTOs.PersonRequestDTO;
import com.kesmark.webapp.models.DTOs.responseDTOs.PersonResponseDTO;
import com.kesmark.webapp.models.exceptions.*;
import com.kesmark.webapp.services.PersoneService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping( produces = "application/json")
public class PersonController {

  private PersoneService personService;


  @GetMapping("/person/{idString}")
  public ResponseEntity<?> findPersonById(@PathVariable String idString){

    Integer id;
    try {
      id = Integer.parseInt(idString);
    } catch (NumberFormatException e) {
      return ResponseEntity.status(406).body(new ErrorDTO("Id must be an integer"));
    }
    try {
      return ResponseEntity.ok().body(personService.findPersonByID(id));
    } catch (IdNotFoundException e) {
      return ResponseEntity.status(406).body(new ErrorDTO(e.getMessage()));
    }

  }

  @PostMapping("/person")
  public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {

    try {
      PersonResponseDTO personResponseDTO = personService.createPerson(personRequestDTO);
      return ResponseEntity.ok().body(personResponseDTO);
    } catch (IllegalArgumentException | InvalidAddresTypeException |
             InvalidContactTypeException e) {
      return ResponseEntity.status(406).body(new ErrorDTO(e.getMessage()));
    }
  }


  @PutMapping("/person/{idString}")
  public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO person,
    @PathVariable String idString) {

    Integer id;
    try {
      id = Integer.parseInt(idString);
    } catch (NumberFormatException e) {
      return ResponseEntity.status(406).body(new ErrorDTO("Id must be an integer"));
    }
    try {
      return ResponseEntity.ok().body(personService.updatePersonByID(id, person));
    } catch (IdNotFoundException e) {
      return ResponseEntity.status(406).body(new ErrorDTO(e.getMessage()));
    }

  }

}
