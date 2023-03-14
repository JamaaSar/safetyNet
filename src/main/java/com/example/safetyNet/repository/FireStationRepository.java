package com.example.safetyNet.repository;

import com.example.safetyNet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static com.example.safetyNet.service.LoadDataFromJson.*;

@Repository
public class FireStationRepository {


    public List<FireStation> getAllFireStation() throws IOException {
        return readJsonFile("firestations", FireStation.class);
    }





}
