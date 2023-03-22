package com.example.safetyNet.controller;

import com.example.safetyNet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/firestation")
public class FireStatonController {

    @Autowired
    FireStationService fireStationService;



    @PostMapping
    public void add() throws IOException {
        fireStationService.add();

    }

    @PutMapping
    public void update() throws IOException {
        fireStationService.update();

    }

    @DeleteMapping
    public void delete() throws IOException {
        fireStationService.delete();

    }

}
