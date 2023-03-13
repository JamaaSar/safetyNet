package com.example.safetyNet.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
public class LoadDataFromJson {

    public static <T>List<T> readJsonFile(String field,  Class<T>  tClass) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData).get(field);
        return mapper.readValue(jsonNode.traverse(), mapper.getTypeFactory().constructCollectionType(List.class, tClass));

    }


}
