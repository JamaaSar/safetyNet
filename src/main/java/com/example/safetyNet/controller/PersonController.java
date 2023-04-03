package com.example.safetyNet.controller;

import com.example.safetyNet.dto.UpdatePersonDTO;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.PersonService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Person> add(@Valid @RequestBody Person person) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.add(person));
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestParam(name="firstname", required = true) String firstName,
                                         @RequestParam(name="lastname", required = true) String lastName, @RequestBody UpdatePersonDTO updatePersonDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(personService.update(firstName, lastName, updatePersonDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<Person>> delete(@RequestParam(name="firstname", required = true) String firstName,
                       @RequestParam(name="lastname", required = true) String lastName) throws IOException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(personService.delete(firstName, lastName));
    }


}
