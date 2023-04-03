package com.example.safetyNet.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends IPerson
{
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private String zip;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Email
    private String email;

}
