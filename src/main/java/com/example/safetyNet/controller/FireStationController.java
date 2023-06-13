package com.example.safetyNet.controller;

import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.service.FireStationService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger =
            LogManager.getLogger("FireStationController");

    @Autowired
    FireStationService fireStationService;

    //ObjectMapper Mapstruct
    @PostMapping
    public ResponseEntity<FireStation> add(
            @Valid @RequestBody FireStation fireStation) {
        logger.info("add firestation");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fireStationService.ajouter(fireStation));
    }

    @GetMapping("/address")
    public ResponseEntity<FireStation> get(
            @RequestParam(name = "address") String address
    ) {
        logger.info("get firestation");
        return ResponseEntity.status(HttpStatus.OK)
                .body(fireStationService.getFireStationByAdresse(address));
    }

    @PutMapping
    public ResponseEntity<FireStation> update(
            @RequestParam(name = "address", required = true) String address,
            @RequestBody String station) {
        logger.info("update firestation");
        return ResponseEntity.status(HttpStatus.OK)
                .body(fireStationService.update(address, station));
    }

    @DeleteMapping
    public ResponseEntity<List<FireStation>> delete(
            @RequestParam(name = "address") String address) {
        logger.info("delete firestation");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(fireStationService.delete(address));
    }

}
