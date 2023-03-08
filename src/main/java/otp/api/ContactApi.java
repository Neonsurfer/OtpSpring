package otp.api;

import otp.entity.Contact;
import otp.exceptions.NotFoundException;

import java.util.List;

public interface ContactApi {

    List<Contact> listContact();

    Contact createContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId);

    Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum, int addressId) throws NotFoundException;

    void deleteContact(int id) throws NotFoundException;
}
