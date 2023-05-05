package com.example.safetyNet.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public class Person extends CommonInfoPerson {
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

    public Person() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
