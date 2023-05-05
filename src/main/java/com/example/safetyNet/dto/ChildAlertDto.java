package com.example.safetyNet.dto;

import com.example.safetyNet.model.Person;

import java.util.List;

public class ChildAlertDto {
    public ChildAlertDto() {
    }

    private List<Child> childList;
    private List<Person> adultList;

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public List<Person> getAdultList() {
        return adultList;
    }

    public void setAdultList(List<Person> adultList) {
        this.adultList = adultList;
    }
}
