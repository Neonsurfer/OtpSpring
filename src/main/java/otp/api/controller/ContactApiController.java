package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import otp.api.ContactApi;
import otp.entity.Contact;
import otp.exceptions.FoundException;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;
import otp.service.ContactService;

import java.util.List;

@RestController()
@RequestMapping("/contact")
public class ContactApiController implements ContactApi {

    @Autowired
    ContactService contactService;

    @Override
    @GetMapping(value = "/list", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> listContact() {
        return contactService.listContact();
    }

    @Override
    @PostMapping(value = "/create", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(String email, String phoneNum, String faxNum, String mobileNum, int addressId) throws UnprocessableEntityException, NotFoundException {
        return contactService.createContact(email, phoneNum, faxNum, mobileNum, addressId);
    }

    @Override
    @PutMapping(value = "/modify", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Contact modifyContact(int id, String email, String phoneNum, String faxNum, String mobileNum, int addressId) throws NotFoundException, FoundException {
        return contactService.modifyContact(id, email, phoneNum, faxNum, mobileNum);
    }

    @Override
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(int id) throws NotFoundException {
        contactService.deleteContact(id);
    }
}
