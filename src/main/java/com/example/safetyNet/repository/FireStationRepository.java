package com.example.safetyNet.repository;


import com.example.safetyNet.model.FireStation;

import java.util.List;

public interface FireStationRepository {


    List<FireStation> getFireStationsList();

    void setFireStationsList(List<FireStation> fireStationsList);

    List<FireStation> getFireStationByStation(String param);

    FireStation getFireStationByAddress(String address);

    List<FireStation> ajouter(FireStation fireStation);

    List<FireStation> remove(FireStation fireStation);

}
