package com.example.safetyNet.dto;

import com.example.safetyNet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlertDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<Person> adults;



}
