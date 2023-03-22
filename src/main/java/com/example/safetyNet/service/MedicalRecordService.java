package com.example.safetyNet.service;

import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    public List<MedicalRecord> getAllMedicalRecords() throws IOException {
        return medicalRecordRepository.getMedicalRecordsList();

    }

    public MedicalRecord getAllMedicalRecordsByName(String name) throws IOException {
        return medicalRecordRepository.getMedicalRecordsList().stream().filter(medicalRecord -> (medicalRecord.getFirstName().toLowerCase()).equals(name.toLowerCase())).findFirst().get();

    }

    public void update(){

    }

    public void add(){

    }

    public void delete(){

    }


}
