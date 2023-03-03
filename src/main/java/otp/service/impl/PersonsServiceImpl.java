package otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otp.dao.PersonsDao;
import otp.entity.Persons;
import otp.service.PersonsService;

import java.util.List;

@Service
public class PersonsServiceImpl implements PersonsService {

    @Autowired
    PersonsDao personsDao;

    @Override
    public List<Persons> listPersons() {
        List<Persons> personsList = personsDao.listPersons();
        return null;
    }
}
