package com.example.safetyNet.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends IPerson
{
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

}
