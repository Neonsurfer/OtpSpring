create database otp;
GO
USE otp;

CREATE TABLE person (
  id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
  name VARCHAR(45) NOT NULL,
  id_number VARCHAR(45) NOT NULL UNIQUE
  );

CREATE TABLE address (
  id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
  city VARCHAR(45) NOT NULL,
  state VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL,
  address_line VARCHAR(60) NOT NULL,
  isPermanent BIT NOT NULL DEFAULT 1
  );

CREATE TABLE contact (
  id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
  email VARCHAR(45) NULL,
  phone_num VARCHAR(45) NULL,
  fax_num VARCHAR(45) NULL,
  mobile_num VARCHAR(45) NULL,
  address_id INT NOT NULL,
  CONSTRAINT FK_contact_address FOREIGN KEY (address_id)
  REFERENCES address (id)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
  );

CREATE TABLE person_address (
  person_id INT NOT NULL,
  address_id INT NOT NULL,
  CONSTRAINT PK_person_address PRIMARY KEY (address_id, person_id),
  CONSTRAINT FK_person_address_person FOREIGN KEY (person_id)
  REFERENCES person (id)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
  CONSTRAINT FK_person_address_address FOREIGN KEY (address_id)
  REFERENCES address (id)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
  );