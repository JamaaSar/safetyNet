package com.example.safetyNet.controller;

import com.example.safetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;



    @PostMapping
    public void add() throws IOException {
        medicalRecordService.add();

    }

    @PutMapping
    public void update() throws IOException {
        medicalRecordService.update();

    }

    @DeleteMapping
    public void delete() throws IOException {
        medicalRecordService.delete();

    }

}
