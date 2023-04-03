package com.example.safetyNet.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPerson
{
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;




}
