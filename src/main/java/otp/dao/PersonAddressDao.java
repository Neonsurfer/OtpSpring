package otp.dao;

import otp.entity.PersonAddress;

public interface PersonAddressDao {

    StringBuilder COUNT_CONNECTION = new StringBuilder()
            .append("SELECT COUNT (*) \n")
            .append("FROM PersonAddress personAddress \n")
            .append("INNER JOIN Person person ON personAddress.person = person \n")
            .append("INNER JOIN Address address ON personAddress.address = address \n")
            .append("WHERE 1=1 \n");

    StringBuilder SELECT_CONNECTION = new StringBuilder()
            .append("SELECT new \n").append(PersonAddress.class.getCanonicalName()).append("( \n")
            .append("personAddress.id, person.id, address.id) \n")
            .append("FROM PersonAddress personAddress \n")
            .append("INNER JOIN Person person ON personAddress.person = person \n")
            .append("INNER JOIN Address address ON personAddress.address = address \n")
            .append("WHERE 1=1 \n");

    int countAddressConnectionByPersonId(int personId);

    PersonAddress addPersonAddressConnection(int personId, int addressId);

    boolean existsConnection(int personId, int addressId);

    void deleteConnection(int personId, int addressId);
}
