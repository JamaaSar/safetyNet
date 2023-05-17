package com.example.safetyNet.repository;

import com.example.safetyNet.model.MedicalRecord;

import java.util.List;


public interface MedicalRecordRepository {

    List<MedicalRecord> getMedicalRecordsList();

    void setMedicalRecordsList(List<MedicalRecord> medicalRecordsList);

    MedicalRecord getMedicalRecordsByFirstAndLastName(String firstName,
                                                      String lastName);

    List<MedicalRecord> ajouter(MedicalRecord medicalRecord);

    List<MedicalRecord> remove(MedicalRecord medicalRecord);
}
