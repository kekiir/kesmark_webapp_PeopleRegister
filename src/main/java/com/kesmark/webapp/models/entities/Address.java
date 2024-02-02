package com.kesmark.webapp.models.entities;

import com.fasterxml.jackson.annotation.*;
import com.kesmark.webapp.models.enums.AddressType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Integer id;

  @Column(columnDefinition = "varchar(100) default 'John street'")
  private String line_1;
  @Column(columnDefinition = "varchar(100) default 'Béla köz'")
  private String line_2;
  @Column(columnDefinition = "varchar(100) default '5/4'")
  private String line_3;
  @Column(columnDefinition = "varchar(100) default 'Salgótarján'")
  private String city;
  @Column(columnDefinition = "varchar(100) default 'Nógrád'")
  private String countryProvince;
  @Column(columnDefinition = "varchar(100) default '2000'")
  private Integer zipOrPostcode;
  @Column(columnDefinition = "varchar(100) default 'HUNGARY'")
  private String country;
  @Enumerated(EnumType.STRING)
  @Column(name = "address_type",
    columnDefinition = "ENUM('PERMANENT','TEMPORARY')")
  private AddressType addressType;

  @JsonIgnore
  @ManyToOne
  private Person person;

  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
  private List<Contact> contactList;

  public Address() {
    this.line_1 = "John street";
    this.line_2 = "Béla köz";
    this.line_3 = "5/4";
    this.city = "Salgótarján";
    this.countryProvince = "countryProvince";
    this.zipOrPostcode = 2000;
    this.country = "country";
    this.addressType = AddressType.PERMANENT;
    this.contactList = new ArrayList<>();
  }

}
