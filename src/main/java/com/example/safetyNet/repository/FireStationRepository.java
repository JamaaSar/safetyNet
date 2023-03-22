package com.example.safetyNet.repository;

import com.example.safetyNet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {
    private List<FireStation> fireStationsList;

    public List<FireStation> getFireStationsList() {
        return fireStationsList;
    }

    public void setFireStationsList(List<FireStation> fireStationsList) {
        this.fireStationsList = fireStationsList;
    }










}
