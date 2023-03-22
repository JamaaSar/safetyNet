package com.example.safetyNet.dto;

import com.example.safetyNet.model.IPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PersonDTO extends IPerson
    {
        private String address;
        private int age;
        private String email;
        private String city;
        private String zip;
        private String phone;
        private List<String> medications;
        private List<String> allergies;

    }

