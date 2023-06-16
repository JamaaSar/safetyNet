package com.example.safetyNet.dto;

import com.example.safetyNet.model.Person;

import java.util.List;

public class ChildAlertDTO {
    public ChildAlertDTO() {
    }

    private List<ChildDTO> childList;
    private List<Person> adultList;

    public List<ChildDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildDTO> childList) {
        this.childList = childList;
    }

    public List<Person> getAdultList() {
        return adultList;
    }

    public void setAdultList(List<Person> adultList) {
        this.adultList = adultList;
    }
}
