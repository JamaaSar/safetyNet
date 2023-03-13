package com.example.safetyNet.repository;

import com.example.safetyNet.model.Person;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.example.safetyNet.service.LoadDataFromJson.readJsonFile;

@Repository
public class FireStationRepository {


    public List<Person> getAllPersons() throws IOException {

        return readJsonFile("persons", Person.class);
    }



}
