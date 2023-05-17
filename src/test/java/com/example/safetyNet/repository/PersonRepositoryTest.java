package com.example.safetyNet.repository;


import com.example.safetyNet.exception.NotFoundException;
import com.example.safetyNet.model.Person;
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
class PersonRepositoryIpmlTest {

    @Mock
    private PersonRepository personRepository;
    private List<Person> persons = new ArrayList<>();
    
    private Person personToTest = new Person();

    @BeforeEach
    public void setUp() {

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

        //person 2
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
        List<Person> personsList = personRepository.getPersonsList();

        //THEN
        assertEquals(persons.size(), personsList.size());
    }

    @Test
    void getPersonsListTest() {
        //GIVEN

        //WHEN
        List<Person> personList =
                personRepository.getPersonsList();

        //THEN
        assertEquals(persons.size(), personList.size());
        assertEquals(3, personList.size());
    }

    @Test
    void getPersonByAddressTest() {
        //GIVEN

        //WHEN
        List<Person> personList =
                personRepository.getPersonByAddresse(
                        "2 Test AddressPersonToTestFirstName");

        //THEN
        assertNotNull(personList);
    }

    @Test
    void getPersonByFirstnameLastNameTest() {
        //GIVEN

        //WHEN
        Person person =
                personRepository.getPersonByFirstnameLastName("PersonToTestFirstName",
                        "PersonToTestLastName");

        //THEN
        assertNotNull(person);

    }

    @Test
    void getPersonByFirstnameLastNameErrorTest() {
        //GIVEN
        Person person = new Person();
        person.setFirstName("Person");
        person.setLastName("Person");
        //WHEN


        //THEN
        assertThrows(NotFoundException.class,
                () -> personRepository.getPersonByFirstnameLastName(
                        person.getFirstName(),
                        person.getLastName()));

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
        List<Person> personList =
                personRepository.ajouter(test);

        //THEN
        assertNotNull(personList);
        assertEquals(4, personList.size());

    }

    @Test
    void removeTest() {
        //GIVEN

        //WHEN
        List<Person> personList =
                personRepository.remove(personToTest);

        //THEN
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }

}
