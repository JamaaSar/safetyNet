package com.example.safetyNet.dto;

import com.example.safetyNet.model.CommonInfoPerson;

import java.util.List;

public class PersonGeneralDto extends CommonInfoPerson {

    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

    public PersonGeneralDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
