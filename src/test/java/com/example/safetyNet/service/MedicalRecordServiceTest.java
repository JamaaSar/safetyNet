package com.example.safetyNet.service;

import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
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
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private MedicalRecordService medicalRecordService;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private MedicalRecord testMedicalRecord = new MedicalRecord();
    private UpdateMedicalRecordDTO updateMedicalRecordDTO = new UpdateMedicalRecordDTO();
    private List<String> medications = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        medications.add("med1");
        medications.add("med2");
        medications.add("med3");

        testMedicalRecord.setFirstName("FirstName4");
        testMedicalRecord.setLastName("LastName4");
        testMedicalRecord.setBirthdate("26/04/1983");
        testMedicalRecord.setMedications(medications);
        testMedicalRecord.setAllergies(allergies);

        updateMedicalRecordDTO.setBirthdate("06/04/1985");
        updateMedicalRecordDTO.setMedications(new ArrayList<>());
        updateMedicalRecordDTO.setAllergies(new ArrayList<>());

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
                medicalRecord);
        MedicalRecord result = medicalRecordService.ajouter(medicalRecord);
        // Then.
        assertEquals("LastName3", result.getLastName());
        assertEquals("FirstName3", result.getFirstName());
        assertEquals("26/04/1983", result.getBirthdate());
        
    }

    @Test
    public void testUpdateFireStation() {
        // Given.
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName("LastName3");
        medicalRecord.setFirstName("FirstName3");
        medicalRecord.setBirthdate("06/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);

        when(medicalRecordRepository.getMedicalRecordsByFirstAndLastName("FirstName3",
                "LastName3")).thenReturn(medicalRecord);
        when(medicalRecordService.update("FirstName3", "LastName3",
                updateMedicalRecordDTO)).thenReturn(medicalRecord);
        MedicalRecord result =
                medicalRecordService.update("FirstName3", "LastName3",
                        updateMedicalRecordDTO);
        // Then.
        assertNotNull(result);

    }

    @Test
    public void testUpdateFireStationEmpty() {
        // Given.
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setLastName("LastName3");
        medicalRecord.setFirstName("FirstName3");
        medicalRecord.setBirthdate("06/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        UpdateMedicalRecordDTO updateMedicalRecordDTOEmpty = new UpdateMedicalRecordDTO();
        updateMedicalRecordDTOEmpty.setAllergies(new ArrayList<>());
        updateMedicalRecordDTOEmpty.setMedications(new ArrayList<>());
        updateMedicalRecordDTOEmpty.setBirthdate("");

        when(medicalRecordRepository.getMedicalRecordsByFirstAndLastName("FirstName3",
                "LastName3")).thenReturn(medicalRecord);
        MedicalRecord result =
                medicalRecordService.update("FirstName3", "LastName3",
                        updateMedicalRecordDTOEmpty);
        // Then.
        assertNotNull(result);

    }

    @Test
    public void testRemoveFireStation() {
        // Given.
        medicalRecords.remove(testMedicalRecord);
        // When.
        when(medicalRecordService.delete(testMedicalRecord.getFirstName(),
                testMedicalRecord.getLastName())).thenReturn(medicalRecords);
        List<MedicalRecord> result =
                medicalRecordService.delete(testMedicalRecord.getFirstName(),
                        testMedicalRecord.getLastName());

        // Then.
        assertEquals(medicalRecords.size(), result.size());
    }

}
