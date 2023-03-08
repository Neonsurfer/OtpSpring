USE otp;

INSERT INTO address (city, state, country, address_line, isPermanent)
VALUES 
('Budapest', 'Budapest', 'Hungary', 'Main st 1', 1),
('Budapest', 'Budapest', 'Hungary', 'Main st 2', 0),
('Budapest', 'Budapest', 'Hungary', 'Main st 3', 1),
('Budapest', 'Budapest', 'Hungary', 'Main st 4', 0),
('Budapest', 'Budapest', 'Hungary', 'Main st 5', 1),
('Budapest', 'Budapest', 'Hungary', 'Main st 6', 0),
('Budapest', 'Budapest', 'Hungary', 'Main st 7', 1),
('Budapest', 'Budapest', 'Hungary', 'Main st 8', 0),
('Budapest', 'Budapest', 'Hungary', 'Main st 9', 1);

INSERT INTO person (name, id_number)
VALUES
('Kiss Jozsef', '123456AB'),
('Nagy Jozsef', '123457AC'),
('Kiss Istvan', '123458AD'),
('Nagy Istvan', '123459AE'),
('Kiss Tamas', '123450AF');

INSERT INTO contact (email, phone_num, fax_num, mobile_num, address_id)
VALUES
('test1@gmail.com', '111-222', '061-423-4298', '06201234567', 1),
('test1@gmail.com', '111-222', '061-423-4298', '06201234567', 1),
('test1@gmail.com', '111-222', '061-423-4298', '06201234567', 2),
('test1@gmail.com', '111-222', '061-423-4298', '06201234567', 3);

INSERT INTO person_address(person_id, address_id)
VALUES
(1,1),
(1,2),
(2,3),
(3,4);