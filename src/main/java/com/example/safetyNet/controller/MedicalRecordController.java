package com.example.safetyNet.controller;

import com.example.safetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping
    public void add(@RequestParam(name = "firstName") String firstName,
                    @RequestParam(name = "lastName") String lastName,
                    @RequestParam(name="birthdate") String birthdate,
                    @RequestParam(name="medications") List<String> medications,
                    @RequestParam(name="allergies") List<String> allergies) throws IOException {
        medicalRecordService.add(firstName, lastName, birthdate, medications, allergies);

    }

    @PutMapping
    public void update(@RequestParam(name = "firstName") String firstName,
                       @RequestParam(name = "lastName") String lastName,
                       @RequestParam(name="birthdate") String birthdate,
                       @RequestParam(name="medications") List<String> medications,
                       @RequestParam(name="allergies") List<String> allergies) throws IOException {
        medicalRecordService.update(firstName, lastName, birthdate, medications, allergies);

    }

    @DeleteMapping
    public void delete(@RequestParam(name = "firstName") String firstName,
                       @RequestParam(name = "lastName") String lastName) throws IOException {
        medicalRecordService.delete( firstName,  lastName);

    }

}
