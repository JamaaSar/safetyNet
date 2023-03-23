package com.example.safetyNet.service;

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

    public List<MedicalRecord> getAllMedicalRecords() throws IOException {
        return medicalRecordRepository.getMedicalRecordsList();

    }

    public MedicalRecord getAllMedicalRecordsByName(String name) throws IOException {
        return medicalRecordRepository.getMedicalRecordsList().stream().filter(medicalRecord -> (medicalRecord.getFirstName().toLowerCase()).equals(name.toLowerCase())).findFirst().get();

    }

    public void update(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies){

    }

    public void add(String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName(lastName);
        medicalRecord.setFirstName(firstName);
        medicalRecord.setBirthdate(birthdate);
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());

    }

    public void delete(String firstName, String lastName){
        for(MedicalRecord m : medicalRecordRepository.getMedicalRecordsList()){
            if(firstName.equals(m.getFirstName()) && lastName.equals(m.getLastName())){
                medicalRecordRepository.getMedicalRecordsList().remove(m);
            }
        }

    }


}
