package com.example.safetyNet.service;

import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceImplTest {


    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private MedicalRecordService medicalRecordService;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private MedicalRecord testMedicalRecord = new MedicalRecord();
    private List<String> medications = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        medications.add("med1");
        medications.add("med2");
        medications.add("med3");

        allergies.add("allergie1");
        allergies.add("allergie2");
        allergies.add("allergie3");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName("LastName1");
        medicalRecord.setFirstName("FirstName1");
        medicalRecord.setBirthdate("26/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setLastName("LastName2");
        medicalRecord1.setFirstName("FirstName2");
        medicalRecord1.setBirthdate("26/04/1983");
        medicalRecord1.setMedications(medications);
        medicalRecord1.setAllergies(allergies);

        testMedicalRecord.setLastName("LastName4");
        testMedicalRecord.setFirstName("FirstName4");
        testMedicalRecord.setBirthdate("26/04/1983");
        testMedicalRecord.setMedications(medications);
        testMedicalRecord.setAllergies(allergies);

        medicalRecords.add(medicalRecord);
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(testMedicalRecord);
    }

    @Test
    public void testGetAllMedicalRecords() throws IOException {
        // When.
        when(medicalRecordRepository.getMedicalRecordsList()).thenReturn(
                Arrays.asList(testMedicalRecord));


        List<MedicalRecord> result =
                medicalRecordService.getAllMedicalRecords();

        // Then.
        assertNotNull(result);
    }

    @Test
    public void testGetAllMedicalRecordsByName() throws IOException {
        // When.
        when(medicalRecordRepository.getMedicalRecordsList()).thenReturn(
                Arrays.asList(testMedicalRecord));


        MedicalRecord result =
                medicalRecordService.getAllMedicalRecordsByName(
                        testMedicalRecord.getFirstName());

        // Then.
        assertNotNull(result);
    }

    @Test
    public void testGetMedicalRecordsByFirstAndLastName() throws IOException {
        // Then.
        assertThrows(NotFoundException.class,
                () -> medicalRecordService.getMedicalRecordsByFirstAndLastName(
                        testMedicalRecord.getFirstName(),
                        testMedicalRecord.getLastName()));
    }

    @Test
    public void testAddMedicalRecord() {
        // Given.
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName("LastName3");
        medicalRecord.setFirstName("FirstName3");
        medicalRecord.setBirthdate("26/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        // When.
        when(medicalRecordRepository.ajouter(medicalRecord)).thenReturn(
                Arrays.asList(medicalRecord));
        List<MedicalRecord> result = medicalRecordService.ajouter(medicalRecord);
        // Then.
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateFireStation() {
        // Given.
        // When.
        when(medicalRecordRepository.getMedicalRecordsList()).thenReturn(
                Arrays.asList(testMedicalRecord));

        when(medicalRecordRepository.remove(testMedicalRecord)).thenReturn(
                medicalRecords);
        List<MedicalRecord> result =
                medicalRecordService.delete(testMedicalRecord.getFirstName(),
                        testMedicalRecord.getLastName());

        // Then.
        assertEquals(2, result.size());
    }

    @Test
    public void testRemoveFireStation() {
        // Given.
        medicalRecords.remove(testMedicalRecord);
        // When.
        when(medicalRecordRepository.getMedicalRecordsList()).thenReturn(
                Arrays.asList(testMedicalRecord));

        when(medicalRecordRepository.remove(testMedicalRecord)).thenReturn(
                medicalRecords);
        List<MedicalRecord> result =
                medicalRecordService.delete(testMedicalRecord.getFirstName(),
                        testMedicalRecord.getLastName());

        // Then.
        assertEquals(2, result.size());
    }

}
