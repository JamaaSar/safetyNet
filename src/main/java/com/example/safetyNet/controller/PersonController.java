package com.example.safetyNet.controller;

import com.example.safetyNet.dto.UpdatePersonDTO;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger =
            LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Person> add(@Valid @RequestBody Person person) {
        logger.info("add person");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.ajouter(person));
    }

    @GetMapping()
    public ResponseEntity<Person> get(
            @RequestParam(name = "firstname", required = true) String firstName,
            @RequestParam(name = "lastname", required = true) String lastName
    ) {
        logger.info("get person");
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.getPersonByFirstnameLastName(firstName, lastName));
    }

    @PutMapping
    public ResponseEntity<Person> update(
            @RequestParam(name = "firstname", required = true) String firstName,
            @RequestParam(name = "lastname", required = true) String lastName,
            @RequestBody UpdatePersonDTO updatePersonDTO) {
        logger.info("update person");
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.update(firstName, lastName, updatePersonDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<Person>> delete(
            @RequestParam(name = "firstname", required = true) String firstName,
            @RequestParam(name = "lastname", required = true) String lastName) {
        logger.info("deleted person");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(personService.delete(firstName, lastName));
    }


}
