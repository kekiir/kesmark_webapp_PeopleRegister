CREATE TABLE IF NOT EXISTS addresses
(
  address_id        INT NOT NULL AUTO_INCREMENT,
  person_id INT NOT NULL,
  line_1 VARCHAR(100) NOT NULL,
  line_2 VARCHAR(100) NOT NULL,
  line_3 VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  country_province VARCHAR(100) NOT NULL,
  zip_or_postcode INT NOT NULL,
  country VARCHAR(100) NOT NULL,
  address_type ENUM('PERMANENT','TEMPORARY'),
  PRIMARY KEY (address_id),
  FOREIGN KEY (person_id) REFERENCES person (id)

)