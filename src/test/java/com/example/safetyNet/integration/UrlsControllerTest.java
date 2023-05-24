package com.example.safetyNet.integration;


import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.FireStationService;
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
class UrlsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;
    private List<Person> persons = new ArrayList<>();


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

        persons.add(person1);
        persons.add(person2);


    }

    @Test
    void shouldGetCommunityEmail() throws Exception {

        this.mockMvc.perform(
                        get("/communityEmail").param("city", "Culver").contentType(
                                "application/json"))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    public void shouldGetPersonInfo() throws Exception {
        //GIVEN


        this.mockMvc.perform(get("/personInfo").param("firstName"
                , "Foster").param("lastName", "Shepard").contentType(
                "application/json")).andExpect(status().isOk()).andReturn();


    }

    @Test
    public void shouldGetChild() throws Exception {

        this.mockMvc.perform(
                        get("/childAlert").param("address", "1509 Culver St")
                                .contentType("application/json")).andExpect(status().isOk())
                .andReturn();


    }

    @Test
    public void shouldGetFireStationByStationNumber() throws Exception {
        this.mockMvc.perform(
                        get("/firestation").param("stationNumber", "1")
                                .contentType("application/json")).andExpect(status().isOk())
                .andReturn();


    }

    @Test
    public void shouldGetFireStation() throws Exception {
        this.mockMvc.perform(
                        get("/phoneAlert").param("firestation", "1")
                                .contentType("application/json")).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void shouldGetFire() throws Exception {


        this.mockMvc.perform(get("/fire").param("address"
                        , "1509 Culver St").contentType(
                        "application/json"))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    public void shouldGetFlood() throws Exception {

        this.mockMvc.perform(get("/flood/stations").param("stations"
                        , "1,2").contentType(
                        "application/json"))
                .andExpect(status().isOk()).andReturn();


    }

}
