package com.example.safetyNet.dto;


import java.util.List;


public class FloodDTO {

    public FloodDTO() {
    }

    private String address;
    private List<PersonForFloodDTO> personForFloodDTOList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonForFloodDTO> getPersonForFloodDTOList() {
        return personForFloodDTOList;
    }

    public void setPersonForFloodDTOList(
            List<PersonForFloodDTO> personForFloodDTOList) {
        this.personForFloodDTOList = personForFloodDTOList;
    }
}
