package com.example.safetyNet.dto;

import com.example.safetyNet.model.Person;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildAlertDto  {

    private List<Child> childList;
    private List<Person> adultList;


}
