package com.example.safetyNet.repository;


import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FireStationRepositoryTest {

    @Mock
    private FireStationRepositoryImp fireStationRepository;
    private List<FireStation> fireStations = new ArrayList<>();
    private FireStation fireStationToTest = new FireStation();


    @BeforeEach
    public void setUp() {

        FireStation fireStation1 = new FireStation();
        fireStation1.setStation("1");
        fireStation1.setAddress("1 Test");
        FireStation fireStation2 = new FireStation();
        fireStation2.setStation("2");
        fireStation2.setAddress("2 Test");
        FireStation fireStation3 = new FireStation();
        fireStation3.setStation("3");
        fireStation3.setAddress("3 Test");

        fireStationToTest.setStation("4");
        fireStationToTest.setAddress("4 Test");

        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        fireStations.add(fireStation3);

    }

    @Test
    void setFireStationsListTest() {
        //GIVEN
        fireStationRepository.setFireStationsList(fireStations);


        //WHEN
        when(fireStationRepository.getFireStationsList()).thenReturn(fireStations);
        List<FireStation> fireStationList = fireStationRepository.getFireStationsList();

        //THEN
        assertEquals(fireStations.size(), fireStationList.size());
    }


    @Test
    void getFireStationByAddressTest() {
        //GIVEN

        //WHEN
        when(fireStationRepository.getFireStationByAddress("4 Test")).thenReturn(
                fireStationToTest);
        FireStation fireStation =
                fireStationRepository.getFireStationByAddress("4 Test");

        //THEN
        assertEquals("4 Test", fireStation.getAddress());
        assertNotNull(fireStation);
    }

    @Test
    void getFireStationByAddressTestErrorTest() {
        //GIVEN
        FireStation fireStation = new FireStation();
        fireStation.setAddress("test");

        //WHEN
        when(fireStationRepository.getFireStationByAddress(
                fireStation.getAddress())).thenThrow(NotFoundException.class);

        //THEN
        assertThrows(NotFoundException.class,
                () -> fireStationRepository.getFireStationByAddress(
                        fireStation.getAddress()));

    }

    @Test
    void ajouterTest() {
        //GIVEN

        //WHEN
        when(fireStationRepository.ajouter(fireStationToTest)).thenReturn(
                fireStationToTest);
        FireStation fireStation =
                fireStationRepository.ajouter(fireStationToTest);

        //THEN
        assertNotNull(fireStation);
        assertEquals("4", fireStation.getStation());
        assertEquals("4 Test", fireStation.getAddress());

    }

    @Test
    void removeTest() {
        //GIVEN
        fireStations.remove(fireStationToTest);

        //WHEN
        when(fireStationRepository.remove(fireStationToTest)).thenReturn(fireStations);
        List<FireStation> fireStationList =
                fireStationRepository.remove(fireStationToTest);

        //THEN
        assertNotNull(fireStationList);
        assertEquals(3, fireStationList.size());

    }

}
