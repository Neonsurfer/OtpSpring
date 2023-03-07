package otp.dao.impl;

import org.springframework.stereotype.Repository;
import otp.dao.ContactDao;
import otp.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ContactDaoImpl implements ContactDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contact> listContacts() {
        Query query = entityManager.createQuery(SELECT_CONTACT.toString());
        return query.getResultList();
    }

    @Override
    public Contact saveContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId) {
        Contact contact = new Contact(email, phoneNum, faxNum, mobileNum, addressId);
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return contact;
    }

    @Override
    public Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum) {
        Contact contact = entityManager.find(Contact.class, id);
        contact.setEmail(email);
        contact.setPhoneNum(phoneNum);
        contact.setFaxNum(faxNum);
        contact.setMobileNum(mobileNum);
        entityManager.merge(contact);
        entityManager.getTransaction().commit();
        entityManager.flush();

        return entityManager.find(Contact.class, id);
    }

    @Override
    public void deleteContact(int id) {
        Contact c = entityManager.find(Contact.class, id);
        entityManager.remove(c);
        entityManager.getTransaction().commit();
        entityManager.flush();
    }

    @Override
    public boolean existsById(int id) {
        return entityManager.find(Contact.class, id) != null;
    }
}
