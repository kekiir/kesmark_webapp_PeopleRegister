package com.kesmark.webapp.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kesmark.webapp.models.enums.AddressType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.*;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstName;
  private String middleName;
  private String familyName;

  @JsonManagedReference // Ensure addressList is serialized
  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  private List<Address> addressList;

  public Person() {
    this.addressList = new ArrayList<>();
  }

}
