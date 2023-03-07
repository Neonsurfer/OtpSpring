package otp.api;

import otp.entity.Contact;
import otp.exceptions.FoundException;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.List;

public interface ContactApi {

    List<Contact> listContact();

    Contact createContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId) throws UnprocessableEntityException, NotFoundException;

    Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum, int addressId) throws NotFoundException, FoundException;

    void deleteContact(int id) throws NotFoundException;
}
