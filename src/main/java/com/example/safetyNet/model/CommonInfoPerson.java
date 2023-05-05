package com.example.safetyNet.model;

import jakarta.validation.constraints.NotEmpty;


public class CommonInfoPerson {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;


    public CommonInfoPerson() {
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
