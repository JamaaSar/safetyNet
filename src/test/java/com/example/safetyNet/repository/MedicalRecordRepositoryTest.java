package com.example.safetyNet.repository;


import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MedicalRecordRepositoryImplTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;
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

        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setLastName("LastName3");
        medicalRecord2.setFirstName("FirstName3");
        medicalRecord2.setBirthdate("26/04/1983");
        medicalRecord2.setMedications(medications);
        medicalRecord2.setAllergies(allergies);

        testMedicalRecord.setLastName("LastName4");
        testMedicalRecord.setFirstName("FirstName4");
        testMedicalRecord.setBirthdate("26/04/1983");
        testMedicalRecord.setMedications(medications);
        testMedicalRecord.setAllergies(allergies);

        medicalRecords.add(medicalRecord);
        medicalRecords.add(medicalRecord1);
        medicalRecords.add(medicalRecord2);
        medicalRecords.add(testMedicalRecord);
        medicalRecordRepository.setMedicalRecordsList(medicalRecords);

    }

    @Test
    void setFireStationsListTest() {
        //GIVEN

        //WHEN
        List<MedicalRecord> medicalRecordList =
                medicalRecordRepository.getMedicalRecordsList();

        //THEN
        assertEquals(medicalRecords.size(), medicalRecordList.size());
    }

    @Test
    void getMedicalRecordByNameTest() {
        //GIVEN

        //WHEN
        MedicalRecord medicalRecord =
                medicalRecordRepository.getMedicalRecordByName("FirstName4");

        //THEN
        assertNotNull(medicalRecord);

    }

    @Test
    void getMedicalRecordByNameErrorTest() {
        //GIVEN
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("test");

        //WHEN


        //THEN
        assertThrows(NotFoundException.class,
                () -> medicalRecordRepository.getMedicalRecordByName(
                        medicalRecord.getFirstName()));

    }


    @Test
    void ajouterTest() {
        //GIVEN
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setLastName("LastNameTest");
        medicalRecordTest.setFirstName("FirstNameTest");
        medicalRecordTest.setBirthdate("26/04/1983");
        medicalRecordTest.setMedications(new ArrayList<String>());
        medicalRecordTest.setAllergies(new ArrayList<String>());

        //WHEN
        List<MedicalRecord> medicalRecordList =
                medicalRecordRepository.ajouter(medicalRecordTest);

        //THEN
        assertNotNull(medicalRecordList);
        assertEquals(5, medicalRecordList.size());

    }

    @Test
    void removeTest() {
        //GIVEN

        //WHEN
        List<MedicalRecord> medicalRecordList =
                medicalRecordRepository.remove(testMedicalRecord);

        //THEN
        assertNotNull(medicalRecordList);
        assertEquals(3, medicalRecordList.size());

    }

}
