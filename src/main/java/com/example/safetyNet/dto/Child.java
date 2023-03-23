package com.example.safetyNet.dto;

import com.example.safetyNet.model.IPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Child extends IPerson {

    private int age;

}
