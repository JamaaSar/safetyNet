package com.example.safetyNet.repository;

import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.safetyNet.service.LoadDataFromJson.readJsonFile;
import static com.example.safetyNet.service.LoadDataFromJson.readJsonFileFilter;

@Repository
public class MedicalRecordRepository {


    public List<MedicalRecord> getAllMedicalRecords() throws IOException {

        return readJsonFile("medicalrecords", MedicalRecord.class);
    }

    public MedicalRecord getAllMedicalRecordsByName(String key, String value) throws IOException {

        return readJsonFileFilter("medicalrecords", key, value, MedicalRecord.class);
    }

}
