package com.kesmark.webapp.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  private Address permanentAdress;

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  private Address temporaryAdress;

}
