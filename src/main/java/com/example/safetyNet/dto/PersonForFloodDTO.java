package com.example.safetyNet.dto;

import com.example.safetyNet.model.CommonInfoPerson;

import java.util.List;

public class PersonForFloodDTO extends CommonInfoPersonDTO {
    private String phone;
    private int age;
    private List<String> medications;
    private List<String> allergies;

    public PersonForFloodDTO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
