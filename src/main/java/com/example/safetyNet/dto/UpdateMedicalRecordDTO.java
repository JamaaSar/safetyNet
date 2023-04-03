package com.example.safetyNet.dto;

import com.example.safetyNet.model.IPerson;
import lombok.Data;

import java.util.List;

@Data
public class UpdateMedicalRecordDTO
{
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

}
