package com.example.safetyNet.repository;

import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl
        implements MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordsList;

    @Override
    public List<MedicalRecord> getMedicalRecordsList() {
        return medicalRecordsList;
    }

    @Override
    public void setMedicalRecordsList(List<MedicalRecord> medicalRecordsList) {
        this.medicalRecordsList = medicalRecordsList;
    }

    @Override
    public MedicalRecord getMedicalRecordsByFirstAndLastName(String firstName,
                                                             String lastName) {
        return medicalRecordsList.stream()
                .filter(medicalRecord ->
                        (medicalRecord.getFirstName().toLowerCase()).equals(
                                firstName.toLowerCase()) &&
                                (medicalRecord.getLastName().toLowerCase()).equals(
                                        lastName.toLowerCase())).findFirst().orElseThrow(
                        () -> new NotFoundException(
                                "Medical record for person with firstname '" + firstName +
                                        "' and lastname '" + lastName + "' not found"));
    }

    @Override
    public List<MedicalRecord> ajouter(MedicalRecord medicalRecord) {
        medicalRecordsList.add(medicalRecord);
        return medicalRecordsList;
    }

    @Override
    public List<MedicalRecord> remove(MedicalRecord medicalRecord) {
        medicalRecordsList.remove(medicalRecord);
        return medicalRecordsList;
    }


}
