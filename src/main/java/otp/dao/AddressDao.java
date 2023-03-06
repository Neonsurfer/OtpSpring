package otp.dao;

import otp.entity.Address;

import java.util.List;

public interface AddressDao {
    StringBuilder SELECT_ADDRESS = new StringBuilder()
            .append("SELECT address.id, address.city, address.state, address.country, address.addressLine \n")
            .append("FROM Address address \n");

    List<Address> listAddresses();

    Address saveAddress(String city, String state, String country, String addressLine);

    Address modifyAddress(int id, String city, String state, String country, String addressLine);

    void deleteAddress(int id);

    boolean existsById(int id);
}
