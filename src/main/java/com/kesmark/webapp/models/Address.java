package com.kesmark.webapp.models;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Integer id;

  private String line_1;
  private String line_2;
  private String line_3;
  private String city;
  private String countryProvince;
  private Integer zipOrPostcode;
  private String country;
  @Enumerated(EnumType.STRING)
  @Column(name = "address_type",
    columnDefinition = "ENUM('PERMANENT','TEMPORARY')")
  private AddressType addressType;

  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private  Person person;

  @OneToMany(mappedBy = "address",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private List<Contact> contactList;
}
