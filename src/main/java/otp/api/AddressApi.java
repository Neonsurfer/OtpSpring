package otp.api;

import otp.entity.Address;
import otp.exceptions.NotFoundException;

import java.util.List;

public interface AddressApi {

    List<Address> listAddress();

    Address createAddress(String city, String state, String country, String addressLine);

    Address modifyAddress(int id, String city, String state, String country, String addressLine) throws NotFoundException;

    void deleteAddress(int id) throws NotFoundException;
}
