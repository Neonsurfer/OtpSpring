package otp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import otp.api.PersonsApi;
import otp.entity.Persons;
import otp.service.PersonsService;

import java.util.List;

@RestController
public class PersonsApiController implements PersonsApi {

    @Autowired
    PersonsService personsService;

    @Override
    @GetMapping(value = "/list", produces = {"application/json"})
    @ResponseBody
    public List<Persons> listPersons() {
        return personsService.listPersons();
    }


    @GetMapping(value = "/")
    @ResponseBody
    public String hello() {
        return "Hello!";
    }
}
