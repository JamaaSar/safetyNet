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
    public void add(@RequestParam(name = "address") String address,
                    @RequestParam(name = "station") String station) throws IOException {
        fireStationService.add(address,station);

    }

    @PutMapping
    public void update(@RequestParam(name = "address") String address,
                       @RequestParam(name = "station") String station) throws IOException {
        fireStationService.update(address, station);

    }

    @DeleteMapping
    public void delete(@RequestParam(name = "address") String address,
                       @RequestParam(name = "station") String station) throws IOException {
        fireStationService.delete(address,station);

    }

}
