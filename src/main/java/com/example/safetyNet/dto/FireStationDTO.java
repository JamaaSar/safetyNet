package com.example.safetyNet.dto;

import jakarta.validation.constraints.NotEmpty;

public class FireStationDTO {
    @NotEmpty
    private String address;
    @NotEmpty
    private String station;

    public FireStationDTO() {
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
