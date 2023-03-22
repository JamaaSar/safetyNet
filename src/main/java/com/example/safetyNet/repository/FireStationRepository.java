package com.example.safetyNet.repository;

import com.example.safetyNet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class FireStationRepository {
    private List<FireStation> fireStationsList;
    private List<FireStation> fireStationsByStation;

    public List<FireStation> getFireStationsByStation(String param) {
        return fireStationsList.stream().filter(fireStation -> fireStation.getStation().equals(param)).collect(Collectors.toList());
    }

    public void setFireStationsByStation(List<FireStation> fireStationsByStation) {
        this.fireStationsByStation = fireStationsByStation;
    }



    public List<FireStation> getFireStationsList() {
        return fireStationsList;
    }

    public void setFireStationsList(List<FireStation> fireStationsList) {
        this.fireStationsList = fireStationsList;
    }










}
