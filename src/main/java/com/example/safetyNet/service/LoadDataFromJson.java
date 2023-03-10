package com.example.safetyNet.service;

import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
public class LoadDataFromJson {

    @Autowired
    PersonRepository personRepository;
    @PostConstruct
    public void loadData () throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));

        List<Person> persons = readJsonFile(jsonData,"persons");
        personRepository.setPersonList(persons);

        List<FireStation> fireStations = readJsonFile(jsonData,"firestations");
        System.out.println(fireStations);
    }
    public static <T>List<T> readJsonFile(byte[] jsonData, String field) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
        };
        JsonNode jsonNode = mapper.readTree(jsonData).path(field);
        return mapper.convertValue(jsonNode, typeReference);

    }

}
