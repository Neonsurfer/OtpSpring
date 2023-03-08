package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import otp.api.PersonApi;
import otp.entity.Person;
import otp.entity.PersonAddress;
import otp.exceptions.NotFoundException;
import otp.exceptions.UnprocessableEntityException;
import otp.service.PersonService;

import java.util.List;

@RestController()
@RequestMapping("/person")
public class PersonApiController implements PersonApi {

    @Autowired
    PersonService personService;

    @Override
    @GetMapping(value = "/list", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public List<Person> listPerson() {
        return personService.listPerson();
    }

    @Override
    @PostMapping(value = "/create", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "idNumber") String idNumber) throws UnprocessableEntityException {
        return personService.createPerson(name, idNumber);
    }

    @Override
    @PutMapping(value = "/modify", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Person modifyPerson(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "idNumber") String idNumber) throws NotFoundException, UnprocessableEntityException {
        return personService.modifyPerson(id, name, idNumber);
    }

    @Override
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(
            @RequestParam(value = "id") int id) throws NotFoundException {
        personService.deletePerson(id);
    }

    @Override
    @PostMapping(value = "/createconn", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public PersonAddress createPersonAddressConnection(
            @RequestParam(value = "personId") int personId,
            @RequestParam(value = "addressId") int addressId) throws UnprocessableEntityException, NotFoundException {
        return personService.createPersonAddressConnection(personId, addressId);
    }

    @Override
    @DeleteMapping(value = "/deleteconn")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonAddressConnection(
            @RequestParam(value = "personId") int personId,
            @RequestParam(value = "addressId") int addressId) throws NotFoundException {
        personService.deletePersonAddressConnection(personId, addressId);
    }
}
