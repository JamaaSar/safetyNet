package com.example.safetyNet.model;

import jakarta.validation.constraints.NotEmpty;

public class FireStation {
    @NotEmpty
    private String address;
    @NotEmpty
    private String station;

    public FireStation() {
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
