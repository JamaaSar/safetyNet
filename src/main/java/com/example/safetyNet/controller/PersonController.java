package com.example.safetyNet.controller;

import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<?> getAllPerson() throws IOException {
        List<Person> personList = personService.getAllPerson();
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void add(@RequestParam(name = "firstName") String firstName,
                    @RequestParam(name = "lastName") String lastName,
                    @RequestParam(name="address") String address,
                    @RequestParam(name="city") String city,
                    @RequestParam(name="zip") String zip,
                    @RequestParam(name="phone") String phone,
                    @RequestParam(name="email") String email) throws IOException {
        personService.add(firstName, lastName, address,city,zip,phone,email);

    }

    @PutMapping
    public void update(@RequestParam(name = "firstName", required = false) String firstName,
                       @RequestParam(name = "lastName", required = false) String lastName,
                       @RequestParam(name="address", required = false) String address,
                       @RequestParam(name="city", required = false) String city,
                       @RequestParam(name="zip", required = false) String zip,
                       @RequestParam(name="phone", required = false) String phone,
                       @RequestParam(name="email", required = false) String email) throws IOException {
        personService.update(firstName, lastName, address,city,zip,phone,email);

    }

    @DeleteMapping
    public void delete(@RequestParam(name = "firstName", required = true) String firstName,
                       @RequestParam(name = "lastName", required = true) String lastName) throws IOException {
        personService.delete(firstName, lastName);

    }

}
