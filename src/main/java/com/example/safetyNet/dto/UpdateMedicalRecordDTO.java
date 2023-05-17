package com.example.safetyNet.dto;


import java.util.List;

public class UpdateMedicalRecordDTO {
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

    public UpdateMedicalRecordDTO() {
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
