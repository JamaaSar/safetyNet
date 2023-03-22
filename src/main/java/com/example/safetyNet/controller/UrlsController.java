package com.example.safetyNet.controller;

import com.example.safetyNet.dto.ChildAlertDto;
import com.example.safetyNet.dto.FireDto;
import com.example.safetyNet.service.FireStationService;
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
    @Autowired
    FireStationService fireStationService;

    @GetMapping("/communityEmail")
    public ResponseEntity getEm(@RequestParam(name = "city", required = true) String city) throws IOException {
        List<?> personList = personService.getAllEmails(city);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/personInfo")
    public ResponseEntity getPersonInfo(@RequestParam(name = "firstName", required = true) String firstName, @RequestParam(name = "lastName", required = true) String lastName) throws IOException {
        List<?> personList = personService.getPersonInfo(firstName, lastName);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/childAlert")
    public ResponseEntity getChild(@RequestParam(name = "address", required = true) String address) throws IOException {
        List<ChildAlertDto> personList = personService.getChildAlert(address);
        if (!personList.isEmpty()) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/firestation")
    public  ResponseEntity getFireStationByStationNumber(@RequestParam(name="stationNumber", required = true) String stationNumber) throws IOException {
        List<Map<String, List<?>>> result = fireStationService.getFireStaionById(stationNumber);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/phoneAlert")
    public  ResponseEntity getFireStation(@RequestParam(name="firestation", required = true) String firestation) throws IOException {
        List<List<?>>result = fireStationService.getFireStaion(firestation);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fire")
    public  ResponseEntity getFire(@RequestParam(name="address", required = true) String address) throws IOException {
        List<FireDto> result = fireStationService.getFire(address);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/flood")
    public  ResponseEntity getFlood(@RequestParam(name="stations", required = true) List<String> stations) throws IOException {
        List<List<?>>result = fireStationService.getFlood(stations);

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }


}
