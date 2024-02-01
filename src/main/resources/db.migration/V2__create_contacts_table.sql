CREATE TABLE IF NOT EXISTS contacts (

  contact_id        INT NOT NULL AUTO_INCREMENT,
  address_id        INT NOT NULL,
  contaxt_type ENUM('EMAIL','PHONE','OTHER') NOT NULL,
PRIMARY KEY (contact_id),
FOREIGN KEY (address_id) REFERENCES adresses (address_id)


)