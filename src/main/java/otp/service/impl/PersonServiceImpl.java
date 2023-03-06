package otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otp.dao.PersonDao;
import otp.entity.Person;
import otp.exceptions.FoundException;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;
import otp.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public List<Person> listPerson() {
        return personDao.listPersons();
    }

    @Override
    public Person createPerson(String name, String idNumber) throws UnprocessableEntityException {
        if (!personDao.existsByIdNumber(idNumber)) {
            throw new UnprocessableEntityException("Ezzel az személyazonosítóval már létezik személy! \n");
        }
        return personDao.savePerson(name, idNumber);
    }

    @Override
    public Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, FoundException {
        if (!personDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található személy! \n");
        }
        if (personDao.existsByIdNumber(idNumber)) {
            throw new FoundException("Ezzel a személyazonosítóval már létezik személy! \n");
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


}
