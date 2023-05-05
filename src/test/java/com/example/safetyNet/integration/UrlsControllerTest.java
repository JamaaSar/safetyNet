package com.example.safetyNet.controller;


import com.example.safetyNet.dto.ChildAlertDto;
import com.example.safetyNet.dto.FloodDTO;
import com.example.safetyNet.dto.PersonGeneralDto;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.FireStationService;
import com.example.safetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UrlsController.class)
@AutoConfigureMockMvc(addFilters = false)
class UrlsControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private FireStationService fireStationService;
//    @MockBean
//    private PersonService personService;
//
//    private final String url = "/";
//    @Autowired
//    private ObjectMapper objectMapper;
//    private List<Person> persons = new ArrayList<>();
//
//    private PersonGeneralDto personGeneralDto = new PersonGeneralDto();
//
//    void setUp() {
//        //person 1
//        Person person1 = new Person();
//        person1.setFirstName("Test1");
//        person1.setLastName("Test1lastname");
//        person1.setAddress("1 Test Address");
//        person1.setCity("Testcity1");
//        person1.setZip("000001");
//        person1.setPhone("0000001");
//        person1.setEmail("test1@gmail.com");
//
//        //person 2
//        Person person2 = new Person();
//        person2.setFirstName("Test2");
//        person2.setLastName("Test2lastname");
//        person2.setAddress("2 Test Address");
//        person2.setCity("Testcity2");
//        person2.setZip("000002");
//        person2.setPhone("0000002");
//        person2.setEmail("test2@gmail.com");
//
//        persons.add(person1);
//        persons.add(person2);
//
//
//    }
//
//    @Test
//    void shouldGetCommunityEmail() throws Exception {
//        //GIVEN
//        List<String> emails = new ArrayList<>();
//        emails.add("test1@gmail.com");
//
//        //WHEN
//        when(personService.getAllEmails(any(String.class))).thenReturn(
//                emails);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        get("/communityEmail").param("city", "city").contentType(
//                                        "application/json")
//                                .content(objectMapper.writeValueAsString("test1@gmail" +
//                                        ".com")))
//
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(emails), result);
//
//    }
//
//    @Test
//    public void shouldGetPersonInfo() throws Exception {
//        //GIVEN
//        Person person = new Person();
//        person.setFirstName("Person");
//        person.setLastName("Person");
//
//
//        //WHEN
//        when(personService.getPersonInfo(any(String.class),
//                any(String.class))).thenReturn(Arrays.asList(personGeneralDto));
//        MvcResult mvcResult = this.mockMvc.perform(get("/personInfo").param("firstName"
//                                , "Person").param("lastName", "Person").contentType(
//                                "application/json")
//                        .content(objectMapper.writeValueAsString(personGeneralDto)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(personGeneralDto)),
//                result);
//
//    }
//
//    @Test
//    public void shouldGetChild() throws Exception {
//        //GIVEN
//        Person person = new Person();
//        ChildAlertDto childAlertDto = new ChildAlertDto();
//        person.setFirstName("Person");
//        person.setLastName("Person");
//        person.setAddress("1 Address");
//
//        //WHEN
//        when(personService.getChildAlert(
//                any(String.class))).thenReturn(
//                Arrays.asList(childAlertDto));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        get("/childAlert").param("address", "1 Address")
//                                .contentType("application/json")).andExpect(status().isOk())
//                .andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(childAlertDto)),
//                result);
//
//    }
//
//    @Test
//    public void shouldGetFireStationByStationNumber() throws Exception {
//        //GIVEN
//        List<Map<String, List<?>>> list = new ArrayList();
//        //WHEN
//        when(fireStationService.getFireStaionById(any(String.class))).thenReturn(
//                list);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        get("/firestation").param("stationNumber", "1")
//                                .contentType("application/json")).andExpect(status().isOk())
//                .andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(list),
//                result);
//
//    }
//
//    @Test
//    public void shouldGetFireStation() throws Exception {
//        //GIVEN
//        List<List<String>> list = new ArrayList();
//        //WHEN
//        when(fireStationService.getFireStaion(any(String.class))).thenReturn(
//                list);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        get("/phoneAlert").param("firestation", "1")
//                                .contentType("application/json")).andExpect(status().isOk())
//                .andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(list),
//                result);
//    }
//
//    @Test
//    public void shouldGetFire() throws Exception {
//        //GIVEN
//        Person person = new Person();
//        person.setFirstName("Person");
//        person.setLastName("Person");
//
//        personGeneralDto.setAllergies(new ArrayList<>());
//        personGeneralDto.setMedications(new ArrayList<>());
//        personGeneralDto.setAge(25);
//        personGeneralDto.setAddress("");
//        personGeneralDto.setEmail("");
//
//        //WHEN
//        when(fireStationService.getFire(
//                any(String.class))).thenReturn(Arrays.asList(personGeneralDto));
//        MvcResult mvcResult = this.mockMvc.perform(get("/fire").param("address"
//                        , "1 Test").param("lastName", "Person").contentType(
//                        "application/json"))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(personGeneralDto)),
//                result);
//
//    }
//
//    public void shouldGetFlood() throws Exception {
//        //GIVEN
//        Person person = new Person();
//        FloodDTO floodDTO = new FloodDTO();
//        floodDTO.setAddress("1 Test");
//
//        person.setFirstName("Person");
//        person.setLastName("Person");
//
//        //WHEN
//        when(fireStationService.getFlood(
//                Arrays.asList(any(String.class)))).thenReturn(Arrays.asList(floodDTO));
//        MvcResult mvcResult = this.mockMvc.perform(get("/flood").param("stations"
//                        , "1,2").contentType(
//                        "application/json"))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(floodDTO)),
//                result);
//
//    }

}
