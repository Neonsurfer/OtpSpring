package otp.service;

import otp.entity.Person;
import otp.entity.PersonAddress;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.List;

public interface PersonService {

    List<Person> listPerson();

    Person createPerson(String name, String idNumber) throws UnprocessableEntityException;

    Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, UnprocessableEntityException;

    void deletePerson(int id) throws NotFoundException;

    PersonAddress createPersonAddressConnection(int personId, int addressId) throws NotFoundException, UnprocessableEntityException;

    void deletePersonAddressConnection(int personId, int addressId) throws NotFoundException;
}
