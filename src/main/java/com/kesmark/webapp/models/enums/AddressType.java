package com.kesmark.webapp.models.enums;

import jakarta.validation.constraints.NotBlank;

public enum AddressType {
  @NotBlank
  PERMANENT,
  TEMPORARY
}
