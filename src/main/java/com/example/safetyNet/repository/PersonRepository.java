package com.example.safetyNet.repository;

import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.safetyNet.service.LoadDataFromJson.readJsonFile;

@Repository
public class PersonRepository  {


    public List<Person> getPersonList() throws IOException {

        return readJsonFile("persons", Person.class);
    }



}
