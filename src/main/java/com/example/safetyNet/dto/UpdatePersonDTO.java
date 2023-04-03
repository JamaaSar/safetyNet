package com.example.safetyNet.dto;

import com.example.safetyNet.model.IPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonDTO
{
    private String address;
    private String email;
    private String city;
    private String zip;
    private String phone;

}

