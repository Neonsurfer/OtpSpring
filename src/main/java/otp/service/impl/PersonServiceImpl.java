package otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otp.dao.AddressDao;
import otp.dao.PersonAddressDao;
import otp.dao.PersonDao;
import otp.entity.Person;
import otp.entity.PersonAddress;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;
import otp.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    PersonAddressDao personAddressDao;

    @Override
    public List<Person> listPerson() {
        return personDao.listPersons();
    }

    @Override
    public Person createPerson(String name, String idNumber) throws UnprocessableEntityException {
        if (personDao.existsByIdNumber(idNumber)) {
            throw new UnprocessableEntityException("Ezzel az személyazonosítóval már létezik személy! \n");
        }
        return personDao.savePerson(name, idNumber);
    }

    @Override
    public Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, UnprocessableEntityException {
        if (!personDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található személy! \n");
        }
        if (personDao.existsByIdNumber(idNumber)) {
            throw new UnprocessableEntityException("Ezzel a személyazonosítóval már létezik személy! \n");
        }
        return personDao.modifyPerson(id, name, idNumber);
    }

    @Override
    public void deletePerson(int id) throws NotFoundException {
        if (!personDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található személy! \n");
        }
        personDao.deletePerson(id);
    }

    @Override
    public PersonAddress createPersonAddressConnection(int personId, int addressId) throws NotFoundException, UnprocessableEntityException {
        if (!personDao.existsById(personId)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található személy! \n");
        }
        if (!addressDao.existsById(addressId)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található cím! \n");
        }
        if (personAddressDao.countAddressConnectionByPersonId(personId) < 2) {
            return personAddressDao.addPersonAddressConnection(personId, addressId);
        } else {
            throw new UnprocessableEntityException("Ehhez a személyhez már tartozik két cím! \n");
        }
    }

    @Override
    public void deletePersonAddressConnection(int personId, int addressId) throws NotFoundException {
        if (!personAddressDao.existsConnection(personId, addressId)) {
            throw new NotFoundException("Nem létezik ilyen személy-cím kapcsolat! \n");
        } else {
            personAddressDao.deleteConnection(personId, addressId);
        }
    }

}
