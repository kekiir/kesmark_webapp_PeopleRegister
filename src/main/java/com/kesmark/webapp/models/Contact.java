package com.kesmark.webapp.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "contacts")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id")
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(name = "contact_type",
    columnDefinition = "ENUM('EMAIL','PHONE','OTHER')")
  private ContactType contactType;

  @JoinColumn(name = "address_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Address address;

}
