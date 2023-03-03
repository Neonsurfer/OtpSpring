package otp.dao;

import otp.entity.Persons;

import java.util.List;

public interface PersonsDao {
    StringBuilder SELECT_PERSONS = new StringBuilder()
    .append("SELECT persons.name \n")
    .append("FROM Persons persons \n");

    List<Persons> listPersons();
}
