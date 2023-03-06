package otp.dao;

import otp.entity.Person;

import java.util.List;


public interface PersonDao {
    StringBuilder SELECT_PERSON = new StringBuilder()
            .append("SELECT person.id, person.name, person.id_number \n")
            .append("FROM Person person \n");


    List<Person> listPersons();

    Person savePerson(String name, String idNumber);

    Person modifyPerson(int id, String name, String idNumber);

    void deletePerson(int id);

    boolean existsById(int id);

    boolean existsByIdNumber(String idNumber);
}
