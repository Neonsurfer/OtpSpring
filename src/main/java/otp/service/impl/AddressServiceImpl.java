package otp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otp.dao.AddressDao;
import otp.entity.Address;
import otp.exceptions.NotFoundException;
import otp.service.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public List<Address> listAddress() {
        return addressDao.listAddresses();
    }

    @Override
    public Address createAddress(String city, String state, String country, String addressLine, Boolean isPermanent) {
        return addressDao.saveAddress(city, state, country, addressLine, isPermanent);
    }

    @Override
    public Address modifyAddress(int id, String city, String state, String country, String addressLine, Boolean isPermanent) throws NotFoundException {
        if (!addressDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található cím! \n");
        }
        return addressDao.modifyAddress(id, city, state, country, addressLine, isPermanent);
    }

    @Override
    public void deleteAddress(int id) throws NotFoundException {
        if (!addressDao.existsById(id)) {
            throw new NotFoundException("Ezzel az azonosítóval nem található cím! \n");
        }
        addressDao.deleteAddress(id);
    }
}
