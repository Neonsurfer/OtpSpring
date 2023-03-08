package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import otp.api.ContactApi;
import otp.entity.Contact;
import otp.exceptions.NotFoundException;
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
    public Contact createContact(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phoneNum") String phoneNum,
            @RequestParam(value = "faxNum") String faxNum,
            @RequestParam(value = "mobileNum") String mobileNum,
            @RequestParam(value = "addressId") int addressId) {
        return contactService.createContact(email, phoneNum, faxNum, mobileNum, addressId);
    }

    @Override
    @PutMapping(value = "/modify", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Contact modifyContact(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "phoneNum") String phoneNum,
            @RequestParam(value = "faxNum") String faxNum,
            @RequestParam(value = "mobileNum") String mobileNum,
            @RequestParam(value = "addressId") int addressId) throws NotFoundException {
        return contactService.modifyContact(id, email, phoneNum, faxNum, mobileNum);
    }

    @Override
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(int id) throws NotFoundException {
        contactService.deleteContact(id);
    }
}
