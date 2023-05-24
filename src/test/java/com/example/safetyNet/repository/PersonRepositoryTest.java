package com.example.safetyNet.repository;


import com.example.safetyNet.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository;

    private List<Person> persons = new ArrayList<>();

    private Person personToTest = new Person();

    @BeforeEach
    void setUp() {

        //Initialize
        //person 1
        Person person1 = new Person();
        person1.setFirstName("Test1");
        person1.setLastName("Test1Lastname");
        person1.setAddress("1 Test Address");
        person1.setCity("Testcity1");
        person1.setZip("000001");
        person1.setPhone("0000001");
        person1.setEmail("test1@gmail.com");
        //person 2
        Person person2 = new Person();
        person2.setFirstName("Test2");
        person2.setLastName("Test2Lastname");
        person2.setAddress("2 Test Address");
        person2.setCity("Testcity2");
        person2.setZip("000002");
        person2.setPhone("0000002");
        person2.setEmail("test2@gmail.com");
        //person to Test
        personToTest.setFirstName("PersonToTestFirstName");
        personToTest.setLastName("PersonToTestLastName");
        personToTest.setAddress("2 Test AddressPersonToTestFirstName");
        personToTest.setCity("Testcity2");
        personToTest.setZip("000002");
        personToTest.setPhone("0000002");
        personToTest.setEmail("test2@gmail.com");

        persons.add(person1);
        persons.add(person2);
        persons.add(personToTest);
        personRepository.setPersonsList(persons);


    }

    @Test
    void setPersonsListTest() {
        //GIVEN

        //WHEN
        when(personRepository.getPersonsList()).thenReturn(persons);
        //THEN

        List<Person> personsList = personRepository.getPersonsList();
        assertEquals(persons.size(), personsList.size());
    }


    @Test
    void getPersonByAddressTest() {
        //GIVEN

        //WHEN
        when(personRepository.getPersonByAddress(
                "2 Test AddressPersonToTestFirstName")).thenReturn(
                Arrays.asList(personToTest));

        List<Person> personList =
                personRepository.getPersonByAddress(
                        "2 Test AddressPersonToTestFirstName");

        //THEN
        assertEquals(1, personList.size());
        assertNotNull(personList);
    }

    @Test
    void getPersonByFirstnameLastNameTest() {
        //GIVEN

        //WHEN
        when(personRepository.getPersonByFirstnameLastName("PersonToTestFirstName",
                "PersonToTestLastName")).thenReturn(personToTest);

        Person person =
                personRepository.getPersonByFirstnameLastName("PersonToTestFirstName",
                        "PersonToTestLastName");

        //THEN
        assertEquals("PersonToTestFirstName", person.getFirstName());
        assertNotNull(person);

    }

    @Test
    void ajouterTest() {
        //GIVEN
        Person test = new Person();
        test.setFirstName("testFirstName");
        test.setLastName("testLastName");
        test.setAddress("2 Test Address");
        test.setCity("Testcity2");
        test.setZip("000002");
        test.setPhone("0000002");
        test.setEmail("test2@gmail.com");
        //WHEN
        when(personRepository.ajouter(test)).thenReturn(test);
        Person person = personRepository.ajouter(test);

        //THEN
        assertNotNull(person);
        assertEquals("testFirstName", person.getFirstName());
        assertEquals("testLastName", person.getLastName());
        assertEquals("2 Test Address", person.getAddress());
        assertEquals("Testcity2", person.getCity());
        assertEquals("000002", person.getZip());
        assertEquals("0000002", person.getPhone());
        assertEquals("test2@gmail.com", person.getEmail());


    }

    @Test
    void removeTest() {
        //GIVEN
        persons.remove(personToTest);

        //WHEN
        when(personRepository.remove(personToTest)).thenReturn(persons);
        List<Person> personList = personRepository.remove(personToTest);

        //THEN
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }

}
