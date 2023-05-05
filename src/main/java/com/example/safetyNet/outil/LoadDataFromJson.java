package com.example.safetyNet.outil;

import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepositoryImp;
import com.example.safetyNet.repository.MedicalRecordRepositoryImpl;
import com.example.safetyNet.repository.PersonRepositoryIpml;
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
    //component ?
    @Autowired
    PersonRepositoryIpml personRepositoryIpml;
    @Autowired
    FireStationRepositoryImp fireStationRepository;
    @Autowired
    MedicalRecordRepositoryImpl medicalRecordRepositoryImpl;

    @PostConstruct
    public void loadData() throws IOException {

        List<Person> persons = transformJsonToList("persons", Person.class);
        List<MedicalRecord> medicalRecords =
                transformJsonToList("medicalrecords", MedicalRecord.class);
        List<FireStation> fireStations =
                transformJsonToList("firestations", FireStation.class);

        personRepositoryIpml.setPersonsList(persons);
        medicalRecordRepositoryImpl.setMedicalRecordsList(medicalRecords);
        fireStationRepository.setFireStationsList(fireStations);

    }

    public static <T> JsonNode readJson(String field) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonData).get(field);
    }

    public static <T> List<T> transformJsonToList(String field, Class<T> tClass)
            throws IOException {
        JsonNode jsonNode = readJson(field);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonNode.traverse(),
                mapper.getTypeFactory().constructCollectionType(List.class, tClass));
    }


}
