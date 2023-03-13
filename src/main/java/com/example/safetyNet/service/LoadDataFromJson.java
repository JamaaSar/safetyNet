package com.example.safetyNet.service;


import com.example.safetyNet.model.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LoadDataFromJson {

    public static <T>List<T> readJsonFile(String field,  Class<T>  tClass) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData).get(field);
        return mapper.readValue(jsonNode.traverse(), mapper.getTypeFactory().constructCollectionType(List.class, tClass));

    }

    public static <T> T readJsonFileFilter(String field,String key, String value,  Class<T>  tClass) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data.json"));
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(jsonData).get(field);
        for (JsonNode node : jsonNode) {
                if (node.get(key) != null && node.get(key).asText().equals(value)) return mapper.treeToValue(node, tClass);
        }
        return null;
    }


}
