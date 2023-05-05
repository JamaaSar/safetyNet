package com.example.safetyNet.service;


import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.FireStationRepository;
import com.example.safetyNet.repository.MedicalRecordRepository;
import com.example.safetyNet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireSationServiceTest {
    @InjectMocks
    private FireStationService fireStationService;
    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private MapperService mapperService;
    @Mock
    private PersonService personService;
    @Mock
    private MedicalRecordService medicalRecordService;
    private List<FireStation> fireStations = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
    private FireStation testFireStation = new FireStation();
    private MedicalRecord medicalRecord = new MedicalRecord();


    @BeforeEach
    void setUp() {

        //person 1
        Person person1 = new Person();
        person1.setFirstName("Test1");
        person1.setLastName("Test1lastname");
        person1.setAddress("1 Test Address");
        person1.setCity("Testcity1");
        person1.setZip("000001");
        person1.setPhone("0000001");
        person1.setEmail("test1@gmail.com");

        //person 2
        Person person2 = new Person();
        person2.setFirstName("Test2");
        person2.setLastName("Test2lastname");
        person2.setAddress("2 Test Address");
        person2.setCity("Testcity2");
        person2.setZip("000002");
        person2.setPhone("0000002");
        person2.setEmail("test2@gmail.com");

        Person person3 = new Person();
        person3.setFirstName("LastName1");
        person3.setLastName("FirstName1");
        person3.setAddress("2 Test Address");
        person3.setCity("Testcity2");
        person3.setZip("000002");
        person3.setPhone("0000002");
        person3.setEmail("test2@gmail.com");

        persons.add(person1);
        persons.add(person3);
        persons.add(person2);


        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        medications.add("med1");
        medications.add("med2");
        medications.add("med3");

        allergies.add("allergie1");
        allergies.add("allergie2");
        allergies.add("allergie3");


        medicalRecord.setLastName("LastName1");
        medicalRecord.setFirstName("FirstName1");
        medicalRecord.setBirthdate("26/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);


        FireStation fireStation1 = new FireStation();
        fireStation1.setStation("1");
        fireStation1.setAddress("1 Chicago");
        FireStation fireStation2 = new FireStation();
        fireStation2.setStation("2");
        fireStation2.setAddress("2 Chicago");
        FireStation fireStation3 = new FireStation();
        fireStation3.setStation("3");
        fireStation3.setAddress("3 Chicago");
        testFireStation.setStation("0");
        testFireStation.setAddress("0 TestStation");
        fireStations.add(fireStation1);
        fireStations.add(fireStation2);
        fireStations.add(fireStation3);
        fireStations.add(testFireStation);


    }


    @Test
    void testAddFireStation() {
        //GIVEN
        FireStation fireStation = new FireStation();
        fireStation.setStation("1");
        fireStation.setAddress("1 Test");
        //WHEN
        when(fireStationRepository.ajouter(fireStation)).thenReturn(
                Arrays.asList(fireStation));
        List<FireStation> result = fireStationService.ajouter(fireStation);
        //THEN
        assertEquals(1, result.size());
    }

    @Test
    void testRemoveFireStation() {
        //GIVEN
        fireStations.remove(testFireStation);

        //WHEN
        when(fireStationRepository.getFireStationByAddress(
                testFireStation.getAddress())).thenReturn(testFireStation);
        when(fireStationRepository.remove(testFireStation)).thenReturn(fireStations);
        List<FireStation> result =
                fireStationService.delete(testFireStation.getAddress());

        //THEN
        assertEquals(3, result.size());
    }


    @Test
    void testGetFire() throws IOException {
        // GIVEN
        PersonGeneralDto personInfoDto = new PersonGeneralDto();

        //WHEN
        when(personService.getPersonByAddresse("2 Test Address")).thenReturn(persons);
        when(mapperService.getPersonsInfo(persons)).thenReturn(
                Arrays.asList(personInfoDto));

        //THEN
        List<PersonGeneralDto> result = fireStationService.getFire("2 Test Address");
        assertEquals(1, result.size());
    }

    @Test
    void testGetFireReturnNull() throws IOException {
        // GIVEN
        PersonGeneralDto personInfoDto = new PersonGeneralDto();

        //WHEN
        when(personService.getPersonByAddresse("33 Test Address")).thenReturn(persons);
        when(mapperService.getPersonsInfo(persons)).thenReturn(new ArrayList<>());

        //THEN
        assertThrows(NotFoundException.class,
                () -> fireStationService.getFire(
                        "33 Test Address"));
    }


    @Test
    void testGetFireStation() throws IOException {
        // GIVEN
        List<List<String>> list = new ArrayList();
        //WHEN
        when(fireStationRepository.getFireStationByStation("1")).thenReturn(fireStations);
        when(personService.getAllPerson()).thenReturn(persons);
        when(fireStationService.getFireStaion("1")).thenReturn(
                list);


        //THEN
        List<List<String>> result = fireStationService.getFireStaion("1");
        assertEquals(4, result.size());

        // assertThrows(NotFoundException.class,() -> fireStationService.getFire("33 Test Address"));

    }

}
