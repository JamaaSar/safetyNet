package com.example.safetyNet.dto;

import com.example.safetyNet.model.IPerson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonGeneralDto extends IPerson {

    private String address;
    private int age;
    private String email;
    private List<String> medications;
    private List<String> allergies;

}
