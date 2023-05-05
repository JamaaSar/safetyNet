package com.example.safetyNet.dto;

import com.example.safetyNet.model.CommonInfoPerson;
import jakarta.validation.constraints.NotEmpty;


public class Child extends CommonInfoPerson {


    public Child() {
    }

    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
