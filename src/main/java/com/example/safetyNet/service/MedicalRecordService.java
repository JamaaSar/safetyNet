package com.example.safetyNet.service;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecords()  {
        return medicalRecordRepository.getMedicalRecordsList();
    }

    public MedicalRecord getAllMedicalRecordsByName(String name) throws IOException {
        return medicalRecordRepository.getMedicalRecordsList().stream().filter(medicalRecord -> (medicalRecord.getFirstName().toLowerCase()).equals(name.toLowerCase())).findFirst().get();
    }

    public MedicalRecord getAllMedicalRecordsByFirstAndLastName(String firstname, String lastname )  {
        return medicalRecordRepository.getMedicalRecordsList().stream().filter(medicalRecord -> (medicalRecord.getFirstName().toLowerCase()).equals(firstname.toLowerCase()) && (medicalRecord.getLastName().toLowerCase()).equals(lastname.toLowerCase())).findFirst().get();
    }

    public MedicalRecord update(String firstName, String lastName, UpdateMedicalRecordDTO updateMedicalRecordDTO){
        MedicalRecord medicalRecordToUpdate = this.getAllMedicalRecordsByFirstAndLastName(firstName, lastName);
        if(!updateMedicalRecordDTO.getBirthdate().isEmpty()){
            medicalRecordToUpdate.setBirthdate(updateMedicalRecordDTO.getBirthdate());
        }
        if(!updateMedicalRecordDTO.getMedications().isEmpty()){
           // medicalRecordToUpdate.setMedications(updateMedicalRecordDTO.getBirthdate());
        }
        return medicalRecordToUpdate;
    }

    public MedicalRecord add(MedicalRecord medicalRecord){
        this.getAllMedicalRecords().add(medicalRecord);
        return medicalRecord;
    }

    public List<MedicalRecord> delete(String firstName, String lastName){
        for(MedicalRecord m : this.getAllMedicalRecords()){
            if(firstName.equals(m.getFirstName()) && lastName.equals(m.getLastName())){
                medicalRecordRepository.getMedicalRecordsList().remove(m);
            }
        }
        return this.getAllMedicalRecords();
    }
}
