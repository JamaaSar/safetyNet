package com.example.safetyNet.repository;

import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FireStationRepositoryImp
        implements FireStationRepository {

    private List<FireStation> fireStationsList;

    @Override
    public List<FireStation> getFireStationsList() {
        return fireStationsList;
    }

    @Override
    public void setFireStationsList(List<FireStation> fireStationsList) {
        this.fireStationsList = fireStationsList;
    }

    @Override
    public List<FireStation> getFireStationByStation(String param) {
        return fireStationsList.stream()
                .filter(fireStation -> fireStation.getStation().equals(param)).collect(
                        Collectors.toList());

    }

    @Override
    public FireStation getFireStationByAddress(String address) {
        return fireStationsList.stream()
                .filter(fireStation -> fireStation.getAddress().equals(address))
                .findFirst().orElseThrow(() -> new NotFoundException(
                        "Firestation with address '" + address + "' not found"));
    }

    @Override
    public List<FireStation> ajouter(FireStation fireStation) {
        fireStationsList.add(fireStation);
        return fireStationsList;
    }

    @Override
    public List<FireStation> remove(FireStation fireStation) {
        fireStationsList.remove(fireStation);
        return fireStationsList;
    }
}
