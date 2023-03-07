package otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otp.dao.ContactDao;
import otp.entity.Contact;
import otp.exceptions.NotFoundException;
import otp.service.ContactService;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public List<Contact> listContact() {
        return contactDao.listContacts();
    }

    @Override
    public Contact createContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId) {
        return contactDao.saveContact(email, phoneNum, faxNum, mobileNum, addressId);
    }

    @Override
    public Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum) throws NotFoundException {
        if (!contactDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem létezik elérhetőség! \n");
        }
        return contactDao.modifyContact(id, email, phoneNum, faxNum, mobileNum);
    }

    @Override
    public void deleteContact(int id) throws NotFoundException {
        if (!contactDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem létezik elérhetőség! \n");
        }
        contactDao.deleteContact(id);
    }
}
