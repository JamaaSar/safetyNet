package com.example.safetyNet.controller;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    private static final Logger logger =
            LoggerFactory.getLogger(MedicalRecordController.class);
    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord> add(
            @Valid @RequestBody MedicalRecord medicalRecord) {
        logger.info("add medicalRecord");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicalRecordService.ajouter(medicalRecord));
    }

    @GetMapping
    public ResponseEntity<MedicalRecord> get(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName) {
        logger.info("get medicalRecord");
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicalRecordService.getMedicalRecordsByFirstAndLastName(firstName,
                        lastName));
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> update(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestBody UpdateMedicalRecordDTO updateMedicalRecordDTO) {
        logger.info("update medicalRecord");
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicalRecordService.update(firstName, lastName,
                        updateMedicalRecordDTO));
    }

    @DeleteMapping
    public ResponseEntity<List<MedicalRecord>> delete(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName) {
        logger.info("delete medicalRecord");
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(medicalRecordService.delete(firstName, lastName));
    }

}
