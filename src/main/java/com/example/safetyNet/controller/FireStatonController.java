package com.example.safetyNet.controller;

import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.service.FireStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStatonController {

    @Autowired
    FireStationService fireStationService;

    @PostMapping
    public ResponseEntity<FireStation> add(@Valid @RequestBody FireStation fireStation) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(fireStationService.add(fireStation));
    }

    @PutMapping
    public  ResponseEntity<FireStation> update(@RequestParam(name = "address", required = true) String address, String station) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(fireStationService.update(address, station));
    }

    @DeleteMapping
    public ResponseEntity<List<FireStation>> delete(@RequestParam(name = "address") String address) throws IOException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(fireStationService.delete(address));
    }

}
