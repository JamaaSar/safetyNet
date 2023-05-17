package com.example.safetyNet.controller;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<List<MedicalRecord>> add(
            @Valid @RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicalRecordService.ajouter(medicalRecord));
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> update(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestBody UpdateMedicalRecordDTO updateMedicalRecordDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicalRecordService.update(firstName, lastName,
                        updateMedicalRecordDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<MedicalRecord>> delete(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(medicalRecordService.delete(firstName, lastName));
    }

}
