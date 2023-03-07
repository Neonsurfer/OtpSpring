package otp.service;

import otp.entity.Contact;
import otp.exceptions.NotFoundException;

import java.util.List;

public interface ContactService {
    List<Contact> listContact();

    Contact createContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId);

    Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum) throws NotFoundException;

    void deleteContact(int id) throws NotFoundException;
}
