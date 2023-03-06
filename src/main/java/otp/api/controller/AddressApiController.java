package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import otp.api.AddressApi;
import otp.entity.Address;
import otp.exceptions.NotFoundException;
import otp.service.AddressService;

import java.util.List;

@RestController()
@RequestMapping("/address")
public class AddressApiController implements AddressApi {

    @Autowired
    AddressService addressService;


    @Override
    @GetMapping(value = "/list", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<Address> listAddress() {
        return addressService.listAddress();
    }

    @Override
    @PostMapping(value = "/create", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(String city, String state, String country, String addressLine) {
        return addressService.createAddress(city, state, country, addressLine);
    }

    @Override
    @PutMapping(value = "/modify", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Address modifyAddress(int id, String city, String state, String country, String addressLine) throws NotFoundException {
        return addressService.modifyAddress(id, city, state, country, addressLine);
    }

    @Override
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(int id) throws NotFoundException {
        addressService.deleteAddress(id);
    }
}
