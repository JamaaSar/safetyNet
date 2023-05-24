package com.example.safetyNet.integration;


import com.example.safetyNet.model.Person;
import com.example.safetyNet.repository.PersonRepository;
import com.example.safetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    private final String url = "/person";
    @Autowired
    private ObjectMapper objectMapper;
    private Person person = new Person();
    private List<Person> personList = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        person.setFirstName("testPerson");
        person.setLastName("testPerson");
        person.setAddress("1 Test Address");
        person.setCity("Testcity1");
        person.setZip("000001");
        person.setPhone("0000001");
        person.setEmail("test1@gmail.com");
        personList = personRepository.getPersonsList();
    }

    @Test
    void shouldReturnPerson() throws Exception {
        this.mockMvc.perform(
                        get(url).param("firstname", "Foster").param("lastname",
                                        "Shepard")
                                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldAddPersonAndReturn201WhenValidInput() throws Exception {

        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated()).andReturn();

    }

    @Test
    void shouldAddPersonAndReturnWhenValidInput() throws Exception {

        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(new Person())))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    void shouldUpdatePersonAndReturn200WhenValidInput() throws Exception {
        personList.add(person);
        this.mockMvc.perform(
                        put(url).param("firstname", "testPerson").param("lastname", "testPerson")
                                .contentType(
                                        "application" +
                                                "/json")
                                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void shouldDeletePersonAndReturnValidInput() throws Exception {
        personList.add(person);
        this.mockMvc.perform(
                delete(url).param("firstname", "testPerson").param("lastname",
                        "testPerson")).andExpect(status().isNoContent()).andReturn();


    }


}
