package com.example.safetyNet.controller;

import com.example.safetyNet.dto.ChildAlertDTO;
import com.example.safetyNet.dto.FloodDTO;
import com.example.safetyNet.dto.PersonGeneralDTO;
import com.example.safetyNet.service.FireStationService;
import com.example.safetyNet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UrlsController {

    private static final Logger logger = LogManager.getLogger("UrlsController");

    @Autowired
    PersonService personService;
    @Autowired
    FireStationService fireStationService;

    @GetMapping("/communityEmail")
    public ResponseEntity<Set<String>> getCommunityEmail(
            @RequestParam(name = "city") String city) {
        logger.info("getCommunityEmail");
        return new ResponseEntity<>(personService.getAllEmails(city), HttpStatus.OK);
    }

    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonGeneralDTO>> getPersonInfo(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) throws IOException {
        logger.info("getPersonInfo");
        return new ResponseEntity<>(personService.getPersonInfo(firstName, lastName),
                HttpStatus.OK);
    }

    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertDTO>> getChild(
            @RequestParam(name = "address") String address) throws IOException {
        logger.info("getChild");
        return new ResponseEntity<>(personService.getChildAlert(address),
                HttpStatus.OK);
    }

    @GetMapping("/firestation")
    public ResponseEntity getFireStationByStationNumber(
            @RequestParam(name = "stationNumber") String stationNumber)
            throws IOException {
        logger.info("getFireStationByStationNumber");
        return new ResponseEntity<>(
                fireStationService.getFireStationById(stationNumber),
                HttpStatus.OK);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity getFireStation(
            @RequestParam(name = "firestation") String fireStation) throws IOException {
        logger.info("getFireStation");
        return new ResponseEntity<>(fireStationService.getFireStation(fireStation),
                HttpStatus.OK);
    }

    @GetMapping("/fire")
    public ResponseEntity<List<PersonGeneralDTO>> getFire(
            @RequestParam(name = "address") String address) throws IOException {
        logger.info("getFire");
        return new ResponseEntity<>(fireStationService.getFire(address),
                HttpStatus.OK);
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodDTO>> getFlood(
            @RequestParam(name = "stations") List<String> stations) throws IOException {
        logger.info("getFlood");
        return new ResponseEntity<>(fireStationService.getFlood(stations),
                HttpStatus.OK);
    }

}
