package com.example.safetyNet.repository;

import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonRepository  {


    private List<Person> personsList;

    public List<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
    }





}
