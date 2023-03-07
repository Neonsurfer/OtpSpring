package otp.api;

import otp.entity.Person;
import otp.entity.PersonAddress;
import otp.exceptions.FoundException;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;

import java.util.List;

public interface PersonApi {

    List<Person> listPerson();

    Person createPerson(String name, String idNumber) throws UnprocessableEntityException, NotFoundException;

    Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, FoundException;

    void deletePerson(int id) throws NotFoundException;

    PersonAddress createPersonAddressConnection(int personId, int AddressId) throws UnprocessableEntityException, NotFoundException;

    void deletePersonAddressConnection(int personId, int AddressId);
}
