package com.example.safetyNet.controller;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord> add(@Valid @RequestBody MedicalRecord medicalRecord) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.add(medicalRecord));
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> update(@RequestParam(name="firstName") String firstName,
                       @RequestParam(name="lastName") String lastName,
                       @RequestBody UpdateMedicalRecordDTO updateMedicalRecordDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(medicalRecordService.update(firstName, lastName, updateMedicalRecordDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<MedicalRecord>> delete(@RequestParam(name="firstName") String firstName,
                       @RequestParam(name="lastName") String lastName) throws IOException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(medicalRecordService.delete( firstName,  lastName));
    }

}
