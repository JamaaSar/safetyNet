package com.example.safetyNet.service;


import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {


    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    public List<Person> init() {
        List<Person> persons = new ArrayList<>();
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

        persons.add(person1);
        persons.add(person2);

        return persons;
    }

    // @Test
    public void testAddFireStation() {
        List<Person> persons = new ArrayList<>();
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
        // Given.
        Person person3 = new Person();
        person3.setFirstName("Test3");
        person3.setLastName("Test3lastname");
        person3.setAddress("3 Test Address");
        person3.setCity("Testcity3");
        person3.setZip("000003");
        person3.setPhone("0000003");
        person3.setEmail("test3@gmail.com");

        // When.
        when(personRepository.getPersonsList()).thenReturn(persons);
        List<Person> result = personService.ajouter(person3);
        System.out.println(result);


        // Then.
        assertEquals(result.stream().count(), 3);
    }

    @Test
    public void testRemoveFireStation() {
        // Given.

    }
}
