package com.example.safetyNet.dto;


import jakarta.validation.constraints.NotEmpty;

public class CommonInfoPersonDTO {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;


    public CommonInfoPersonDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
