package otp.service;

import otp.entity.Address;
import otp.exceptions.NotFoundException;

import java.util.List;

public interface AddressService {
    List<Address> listAddress();

    Address createAddress(String city, String state, String country, String addressLine, Boolean isPermanent);

    Address modifyAddress(int id, String city, String state, String country, String addressLine, Boolean isPermanent) throws NotFoundException;

    void deleteAddress(int id) throws NotFoundException;
}
