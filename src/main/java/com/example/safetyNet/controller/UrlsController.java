package com.example.safetyNet.controller;

import com.example.safetyNet.dto.ChildAlertDto;
import com.example.safetyNet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UrlsController {
    @Autowired
    PersonService personService;

    @GetMapping("/communityEmail")
    public ResponseEntity<?> getEm(@RequestParam(name = "city", required = true) String city) throws IOException {
        List<?> personList = personService.getAllEmails(city);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/personInfo")
    public ResponseEntity<?> getPersonInfo(@RequestParam(name = "firstName", required = true) String firstName, @RequestParam(name = "lastName", required = true) String lastName) throws IOException {
        List<?> personList = personService.getPersonInfo(firstName, lastName);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/childAlert")
    public ResponseEntity<?> getChild(@RequestParam(name = "address", required = true) String address) throws IOException {
        Map<String, List<?>>  personList = personService.getChild(address);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }

    }


}
