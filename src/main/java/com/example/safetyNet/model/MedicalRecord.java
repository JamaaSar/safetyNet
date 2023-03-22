package com.example.safetyNet.model;

import lombok.Data;
import java.util.List;

@Data
public class MedicalRecord extends IPerson
{
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

}
