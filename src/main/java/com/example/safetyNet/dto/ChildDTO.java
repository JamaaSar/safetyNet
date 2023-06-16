package com.example.safetyNet.dto;

import com.example.safetyNet.model.CommonInfoPerson;


public class ChildDTO extends CommonInfoPersonDTO {


    public ChildDTO() {
    }

    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
