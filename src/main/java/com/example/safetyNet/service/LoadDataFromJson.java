package com.example.safetyNet.service;

import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepository;
import com.example.safetyNet.repository.MedicalRecordRepository;
import com.example.safetyNet.repository.PersonRepository;
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
    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @PostConstruct
    public void loadData () throws IOException {

        List<Person> persons = transformJsonToList("persons", Person.class);
        List<MedicalRecord> medicalRecords = transformJsonToList("medicalrecords", MedicalRecord.class);
        List<FireStation> fireStations = transformJsonToList("firestations", FireStation.class);

        personRepository.setPersonsList(persons);
        medicalRecordRepository.setMedicalRecordsList(medicalRecords);
        fireStationRepository.setFireStationsList(fireStations);

    }

    public static <T> JsonNode readJson(String field) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonData).get(field);
    }
    public static <T>List<T>  transformJsonToList(String field, Class<T>  tClass) throws IOException {
        JsonNode jsonNode = readJson(field);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonNode.traverse(), mapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }
    public static <T> T filterJsonToListByValue(String field,String key, String value, Class<T>  tClass) throws IOException {
        JsonNode jsonNode = readJson(field);
        ObjectMapper mapper = new ObjectMapper();
        for (JsonNode node : jsonNode) {
            if (node.get(key) != null && node.get(key).asText().equals(value)) return mapper.treeToValue(node, tClass);
        }
        return null;
    }

//
//    public static <T>List<T> readJsonFile(String field,  Class<T>  tClass) throws IOException {
//        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(jsonData).get(field);
//        return mapper.readValue(jsonNode.traverse(), mapper.getTypeFactory().constructCollectionType(List.class, tClass));
//    }
//    public static <T> T readJsonFileFilter(String field,String key, String value,  Class<T>  tClass) throws IOException {
//        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode jsonNode = mapper.readTree(jsonData).get(field);
//        for (JsonNode node : jsonNode) {
//            if (node.get(key) != null && node.get(key).asText().equals(value)) return mapper.treeToValue(node, tClass);
//        }
//        return null;
//    }




}
