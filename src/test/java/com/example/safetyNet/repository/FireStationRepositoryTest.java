package com.example.safetyNet.repository;


import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class FireSationRepositoryTest {

    private List<FireStation> fireStations = new ArrayList<>();

    @Mock
    private FireStationRepository fireStationRepository;

    @BeforeEach
    public void setUp() {

        FireStation fireStation1 = new FireStation();
        fireStation1.setStation("1");
        fireStation1.setAddress("1 Chicago");
        FireStation fireStation2 = new FireStation();
        fireStation2.setStation("2");
        fireStation2.setAddress("2 Chicago");
        FireStation fireStation3 = new FireStation();
        fireStation3.setStation("3");
        fireStation3.setAddress("3 Chicago");

        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        fireStations.add(fireStation3);
        fireStationRepository.setFireStationsList(fireStations);

    }

    @Test
    void setFireStationsListTest() {
        //GIVEN

        //WHEN
        List<FireStation> fireStationList = fireStationRepository.getFireStationsList();

        //THEN
        assertEquals(fireStations.size(), fireStationList.size());
    }

    @Test
    void getFireStationByStationTest() {
        //GIVEN

        //WHEN
        List<FireStation> fireStationList =
                fireStationRepository.getFireStationByStation("1");

        //THEN
        assertEquals(1, fireStationList.size());
    }

    @Test
    void getFireStationByAddressTest() {
        //GIVEN

        //WHEN
        FireStation fireStation =
                fireStationRepository.getFireStationByAddress("1 Chicago");

        //THEN
        assertNotNull(fireStation);
    }

    @Test
    void getFireStationByAddressTestErrorTest() {
        //GIVEN
        FireStation fireStation = new FireStation();

        //WHEN


        //THEN
        assertThrows(NotFoundException.class,
                () -> fireStationRepository.getFireStationByAddress(
                        fireStation.getAddress()));

    }

    @Test
    void ajouterTest() {
        //GIVEN

        //WHEN
        List<FireStation> fireStationList =
                fireStationRepository.ajouter(new FireStation("4", "4 Chicago"));

        //THEN
        assertNotNull(fireStationList);
        assertEquals(4, fireStationList.size());

    }

    @Test
    void removeTest() {
        //GIVEN

        //WHEN
        List<FireStation> fireStationList =
                fireStationRepository.remove(new FireStation("4", "4 Chicago"));

        //THEN
        assertNotNull(fireStationList);
        assertEquals(3, fireStationList.size());

    }

}
