CREATE TABLE IF NOT EXISTS contacts (

  contact_id        INT NOT NULL AUTO_INCREMENT,
  address_id        INT NOT NULL,
  contact_type ENUM('EMAIL','PHONE','OTHER') NOT NULL,
  contact VARCHAR(100) NOT NULL,
PRIMARY KEY (contact_id),
FOREIGN KEY (address_id) REFERENCES addresses (address_id)


)