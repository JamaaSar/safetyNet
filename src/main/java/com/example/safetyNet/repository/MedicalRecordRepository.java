package com.example.safetyNet.repository;

import com.example.safetyNet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordsList;
    
    private List<MedicalRecord> medicalRecordsListByName;

    public List<MedicalRecord> getMedicalRecordsList() {
        return medicalRecordsList;
    }

    public void setMedicalRecordsList(List<MedicalRecord> medicalRecordsList) {
        this.medicalRecordsList = medicalRecordsList;
    }

    public MedicalRecord getMedicalRecordsListByName(String name) {
        return medicalRecordsList.stream().filter(medicalRecord -> (medicalRecord.getFirstName().toLowerCase()).equals(name.toLowerCase())).findFirst().get();
    }

    public void setMedicalRecordsListByName(List<MedicalRecord> medicalRecordsListByName ) {
        this.medicalRecordsListByName = medicalRecordsListByName;
    }

    public void add(MedicalRecord medicalRecord ) {
         medicalRecordsList.add(medicalRecord);
    }
    public void remove(MedicalRecord medicalRecord ) {
        medicalRecordsList.remove(medicalRecord);
    }


//    public List<MedicalRecord> getAllMedicalRecords() throws IOException {
//
//        return readJsonFile("medicalrecords", MedicalRecord.class);
//    }
//
 //  public MedicalRecord getAllMedicalRecordsByName(String key, String value) throws IOException {
//
   //     return readJsonFileFilter("medicalrecords", key, value, MedicalRecord.class);
   // }

}
