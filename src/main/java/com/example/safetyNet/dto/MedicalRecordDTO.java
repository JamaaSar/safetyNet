package com.example.safetyNet.dto;

import com.example.safetyNet.model.CommonInfoPerson;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MedicalRecordDTO extends CommonInfoPersonDTO {
    @NotEmpty
    private String birthdate;
    @NotEmpty
    private List<String> medications;
    @NotEmpty
    private List<String> allergies;

    public MedicalRecordDTO() {
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}
