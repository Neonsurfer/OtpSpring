package otp.dao;

import otp.entity.Contact;

import java.util.List;

public interface ContactDao {
    StringBuilder SELECT_CONTACT = new StringBuilder()
            .append("SELECT new ").append(Contact.class.getCanonicalName()).append("( \n")
            .append("contact.id, contact.email, contact.phoneNum, contact.faxNum, contact.mobileNum, ")
            .append("contact.addressId) \n")
            .append("FROM Contact contact; \n");

    List<Contact> listContacts();

    Contact saveContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId);

    Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum);

    void deleteContact(int id);

    boolean existsById(int id);
}
