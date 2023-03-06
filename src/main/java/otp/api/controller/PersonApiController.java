package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import otp.api.PersonApi;
import otp.entity.Person;
import otp.exceptions.FoundException;
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
    public Person createPerson(String name, String idNumber) throws UnprocessableEntityException {
        return personService.createPerson(name, idNumber);
    }

    @Override
    @PutMapping(value = "/modify", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Person modifyPerson(int id, String name, String idNumber) throws NotFoundException, FoundException {
        return personService.modifyPerson(id, name, idNumber);
    }

    @Override
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(int id) throws NotFoundException {
        personService.deletePerson(id);
    }

    //TODO Delete, only for testing
    @GetMapping(value = "/")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }
}
