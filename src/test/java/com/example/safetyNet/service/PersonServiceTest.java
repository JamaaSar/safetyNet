package com.example.safetyNet.service;


import com.example.safetyNet.dto.ChildAlertDto;
import com.example.safetyNet.dto.PersonDTO;
import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.dto.UpdatePersonDTO;
import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.MedicalRecordRepository;
import com.example.safetyNet.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private MapperService mapperService;
    @InjectMocks
    private PersonService personService;


    private Person personToTest = new Person();
    private List<Person> persons = new ArrayList<>();

    private MedicalRecord medicalRecord = new MedicalRecord();

    @BeforeEach
    public void setUp() {

        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        medications.add("med1");
        medications.add("med2");
        medications.add("med3");

        allergies.add("allergie1");
        allergies.add("allergie2");
        allergies.add("allergie3");


        medicalRecord.setFirstName("testPersonFirstName");
        medicalRecord.setLastName("testPersonLastName");
        medicalRecord.setBirthdate("26/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);

        personToTest.setFirstName("testPersonFirstName");
        personToTest.setLastName("testPersonLastName");
        personToTest.setAddress("0 Test Address");
        personToTest.setCity("testcity");
        personToTest.setZip("00000");
        personToTest.setPhone("000000");
        personToTest.setEmail("testperson@gmail.com");

        persons.add(personToTest);
    }

    @Test
    public void testAddPerson() {

        // Given.
        Person person = new Person();
        person.setFirstName("test");
        person.setLastName("test");
        person.setAddress("3 test Address");
        person.setCity("test");
        person.setZip("000001");
        person.setPhone("0000001");
        person.setEmail("test@gmail.com");
        // When.
        when(personRepository.ajouter(person)).thenReturn(person);
        Person result = personService.ajouter(person);

        // Then.
        assertEquals("test", result.getFirstName());
        assertEquals("test", result.getLastName());
        assertEquals("3 test Address", result.getAddress());
        assertEquals("test", result.getCity());
        assertEquals("000001", result.getZip());
        assertEquals("0000001", result.getPhone());
        assertEquals("test@gmail.com", result.getEmail());

    }

    @Test
    public void testRemovePerson() {
        // Given.
        persons.remove(personToTest);
        // When.

        List<Person> result =
                personService.delete(personToTest.getFirstName(),
                        personToTest.getLastName());

        // Then.
        assertEquals(persons.size(), result.size());
    }

    @Test
    public void testGetChildAlert() throws IOException {
        // Given.
        List<PersonDTO> personDTOList = new ArrayList<>();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName("testPersonFirstName");
        personDTO.setLastName("testPersonLastName");
        personDTO.setAddress("0 Test Address");
        personDTO.setAge(40);
        personDTO.setEmail("testperson@gmail.com");
        personDTO.setCity("testcity");
        personDTO.setZip("00000");
        personDTO.setPhone("99999999");
        personDTO.setMedications(new ArrayList<>());
        personDTO.setAllergies(new ArrayList<>());
        PersonDTO personDTO1 = new PersonDTO();
        personDTO1.setFirstName("testChildFirstName");
        personDTO1.setLastName("testChildLastName");
        personDTO1.setAddress("0 Test Address");
        personDTO1.setAge(10);
        personDTO1.setEmail("testperson@gmail.com");
        personDTO1.setCity("testcity");
        personDTO1.setZip("00000");
        personDTO1.setPhone("99999999");
        personDTO1.setMedications(new ArrayList<>());
        personDTO1.setAllergies(new ArrayList<>());
        personDTOList.add(personDTO1);
        personDTOList.add(personDTO);

        // When.
        when(personRepository.getPersonByAddress("0 Test Address")).thenReturn(
                Arrays.asList(personToTest));
        when(mapperService.getAllInfoOfPerson(Arrays.asList(personToTest))).thenReturn(
                personDTOList);

        List<ChildAlertDto> result =
                personService.getChildAlert("0 Test Address");

        // Then.
        assertEquals(persons.size(), result.size());
    }

    @Test
    public void testGetChildAlertError() throws IOException {
        // Given.
        List<Person> emptyList = new ArrayList<>();

        // When.
        when(personRepository.getPersonByAddress("1 Test Address")).thenReturn(
                emptyList);

        // Then.
        assertThrows(NotFoundException.class,
                () -> personService.getChildAlert("1 Test Address"));
    }

    @Test
    public void testAllEmailsError() throws IOException {
        // Given.
        List<Person> emptyList = new ArrayList<>();

        // When.
        when(personRepository.getPersonByCity("testCity")).thenReturn(
                emptyList);

        // Then.
        assertThrows(NotFoundException.class,
                () -> personService.getAllEmails("testCity"));
    }


    @Test
    public void testGetPersonInfo() throws IOException {
        // Given.
        PersonGeneralDto personGeneralDto = new PersonGeneralDto();
        List<PersonGeneralDto> personGeneralDtos = new ArrayList<>();
        personGeneralDtos.add(personGeneralDto);


        // When.
        when(personRepository.getPersonsByFirstnameLastName("testPersonFirstName",
                "testPersonLastName")).thenReturn(persons);
        when(mapperService.getPersonsInfo(persons)).thenReturn(personGeneralDtos);


        List<PersonGeneralDto> result =
                personService.getPersonInfo("testPersonFirstName", "testPersonLastName");

        // Then.
        assertEquals(persons.size(), result.size());
    }


    @Test
    public void testGetPersonInfoError() throws IOException {
        // Given.
        List<Person> emptyList = new ArrayList<>();

        // When.
        when(personRepository.getPersonsByFirstnameLastName("test",
                "test")).thenReturn(emptyList);

        // Then.
        assertThrows(NotFoundException.class,
                () -> personService.getPersonInfo("test",
                        "test"));
    }

    @Test
    public void testGetAllPersons() throws IOException {
        // Given.


        // When.
        when(personRepository.getPersonsList()).thenReturn(persons);

        List<Person> result = personService.getAllPerson();

        // Then.
        assertEquals(persons.size(), result.size());

    }

    @Test
    public void testGetPersonByAddress() throws IOException {
        // Given.


        // When.
        when(personRepository.getPersonByAddress("0 Test Address")).thenReturn(persons);

        List<Person> result = personService.getPersonByAddress("0 Test Address");

        // Then.
        assertEquals(persons.size(), result.size());

    }

    @Test
    public void testUpdatePerson() throws IOException {
        // Given.


        UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO();
        updatePersonDTO.setAddress("");
        updatePersonDTO.setEmail("");
        updatePersonDTO.setCity("");
        updatePersonDTO.setZip("");
        updatePersonDTO.setPhone("");
        // When.
        when(personRepository.getPersonByFirstnameLastName(personToTest.getFirstName(),
                personToTest.getLastName())).thenReturn(personToTest);

        Person result = personService.update(personToTest.getFirstName(),
                personToTest.getLastName(), updatePersonDTO);

        // Then.
        assertNotNull(result);

    }
}
