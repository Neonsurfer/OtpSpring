package otp.dao;

import otp.entity.Address;

import java.util.List;

public interface AddressDao {
    StringBuilder SELECT_ADDRESS = new StringBuilder()
            .append("SELECT new ").append(Address.class.getCanonicalName()).append("( \n")
            .append("address.id, address.city, address.state, address.country, address.addressLine) \n")
            .append("FROM Address address \n")
            .append("WHERE 1=1 \n");

    List<Address> listAddresses();

    Address saveAddress(String city, String state, String country, String addressLine, Boolean isPermanent);

    Address modifyAddress(int id, String city, String state, String country, String addressLine, Boolean isPermanent);

    void deleteAddress(int id);

    boolean existsById(int id);
}
