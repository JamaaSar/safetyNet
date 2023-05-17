package com.example.safetyNet.service;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.getMedicalRecordsList();
    }

    public MedicalRecord getAllMedicalRecordsByName(String name) throws IOException {
        return medicalRecordRepository.getMedicalRecordsList().stream()
                .filter(medicalRecord -> (medicalRecord.getFirstName()
                        .toLowerCase()).equals(name.toLowerCase())).findFirst().get();
    }

    public List<MedicalRecord> ajouter(MedicalRecord medicalRecord) {
        return medicalRecordRepository.ajouter(medicalRecord);
    }

    public MedicalRecord update(String firstName, String lastName,
                                UpdateMedicalRecordDTO updateMedicalRecordDTO) {
        MedicalRecord medicalRecordToUpdate =
                medicalRecordRepository.getMedicalRecordsByFirstAndLastName(firstName,
                        lastName);
        if (!updateMedicalRecordDTO.getBirthdate().isEmpty()) {
            medicalRecordToUpdate.setBirthdate(updateMedicalRecordDTO.getBirthdate());
        }
        if (!updateMedicalRecordDTO.getMedications().isEmpty()) {
            medicalRecordToUpdate.setMedications(updateMedicalRecordDTO.getMedications());
        }
        if (!updateMedicalRecordDTO.getAllergies().isEmpty()) {
            medicalRecordToUpdate.setAllergies(updateMedicalRecordDTO.getAllergies());
        }
        return medicalRecordToUpdate;
    }

    public List<MedicalRecord> delete(String firstName, String lastName) {
        MedicalRecord medicalRecordToDel =
                medicalRecordRepository.getMedicalRecordsByFirstAndLastName(firstName,
                        lastName);
        return medicalRecordRepository.remove(medicalRecordToDel);
    }

}
