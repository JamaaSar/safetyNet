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
    public void add() throws IOException {
        personService.add();

    }

    @PutMapping
    public void update() throws IOException {
        personService.update();

    }

    @DeleteMapping
    public void delete() throws IOException {
        personService.delete();

    }

}
