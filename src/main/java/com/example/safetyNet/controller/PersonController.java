package com.example.safetyNet.controller;

import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @PostMapping
    public Person createPerson(@RequestBody Person person){

        return null;

    }
    @PutMapping
    public Person updatePerson(){
        return null;


    }
    @DeleteMapping
    public void deletePerson(){

    }
}
