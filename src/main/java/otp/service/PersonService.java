package otp.service;

import otp.entity.Person;
import otp.exceptions.FoundException;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.List;

public interface PersonService {

    List<Person> listPerson();

    Person createPerson(String name, String idNumber) throws UnprocessableEntityException;

    Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, FoundException;

    void deletePerson(int id) throws NotFoundException;
}
