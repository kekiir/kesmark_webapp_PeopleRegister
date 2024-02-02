package com.kesmark.webapp.services;

import com.kesmark.webapp.models.DTOs.errorDTOs.ErrorDTO;
import com.kesmark.webapp.models.DTOs.errorDTOs.ErrorListResponseDTO;
import com.kesmark.webapp.models.exceptions.InvalidAddresTypeException;
import com.kesmark.webapp.models.exceptions.InvalidContactTypeException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

@Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
    HttpStatusCode status, WebRequest request) {
    ErrorListResponseDTO errorListResponseDTO = new ErrorListResponseDTO();
    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.toList());
    errorListResponseDTO.setMessage(errors);

    return new ResponseEntity<>(errorListResponseDTO, HttpStatus.BAD_REQUEST);
  }

  @Override
  @ApiResponse(responseCode = "423", description = "JSON is not readable.",
    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
    HttpStatusCode status, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO("JSON is not readable.");

    return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(InvalidAddresTypeException.class)
  @ApiResponse(responseCode = "401", description = "Invalid addres type. Valid types: PERMANENT or TEMPORARY",
    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  public ResponseEntity<ErrorDTO> handleInvalidAddressTypeException(InvalidAddresTypeException ex) {
    ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDTO);
  }

  @ExceptionHandler(InvalidContactTypeException.class)
  @ApiResponse(responseCode = "401", description = "Invalid contact type. Valid types: PHONE, EMAIL, OTHER",
    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
  public ResponseEntity<ErrorDTO> handleInvalidContactTypeException(InvalidContactTypeException ex) {
    ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDTO);
  }


}
