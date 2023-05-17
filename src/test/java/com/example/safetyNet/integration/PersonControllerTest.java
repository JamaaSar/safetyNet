package com.example.safetyNet.controller;


import com.example.safetyNet.dto.UpdatePersonDTO;
import com.example.safetyNet.model.Person;
import com.example.safetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private PersonService personService;
//
//    private final String url = "/person";
//    @Autowired
//    private ObjectMapper objectMapper;
//    private Person person = new Person();
//    private List<Person> persons = new ArrayList<>();
//    private List<String> allergies = new ArrayList<>();
//
//    @BeforeEach
//    public void setUp() {
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
//    }
//
//    @Test
//    void shouldAddPersonAndReturn201WhenValidInput() throws Exception {
//        //GIVEN
//        person.setFirstName("testPerson");
//        person.setLastName("testPerson");
//        person.setAddress("1 Test Address");
//        person.setCity("Testcity1");
//        person.setZip("000001");
//        person.setPhone("0000001");
//        person.setEmail("test1@gmail.com");
//        //WHEN
//        when(personService.ajouter(any(Person.class))).thenReturn(
//                Arrays.asList(person));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isCreated()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(person)),
//                result);
//
//    }
//
//    @Test
//    void shouldAddPersonAndReturnWhenValidInput() throws Exception {
//        //GIVEN
//
//        //WHEN
//        when(personService.ajouter(any(Person.class))).thenReturn(
//                Arrays.asList(new Person()));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(new Person())))
//                .andExpect(status().isBadRequest()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        Pattern re =
//                Pattern.compile("\\bfirstName|lastName|phone|email|zip|city|address\\b",
//                        Pattern.CASE_INSENSITIVE);
//        assertEquals(true, re.matcher(result).find());
//
//    }
//
//    @Test
//    void shouldUpdatePersonAndReturn200WhenValidInput() throws Exception {
//        //GIVEN
//
//        //WHEN
//        when(personService.update(any(String.class), any(String.class),
//                any(UpdatePersonDTO.class))).thenReturn(
//                person);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        put(url).param("firstname", "firstname").param("lastname", "lastname")
//                                .contentType(
//                                        "application" +
//                                                "/json")
//                                .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(person), result);
//
//    }
//
//    @Test
//    void shouldUpdatePersonAndReturnValidInput() throws Exception {
//
//        //WHEN
//        when(personService.update(any(String.class), any(String.class),
//                any(UpdatePersonDTO.class))).thenReturn(
//                person);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        put(url).param("firstname", "firstname").param("lastname", "lastname")
//                                .contentType("application" + "/json")
//                                .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(person), result);
//
//    }
//
//    @Test
//    void shouldDeletePersonAndReturnValidInput() throws Exception {
//
//
//        when(personService.delete(any(String.class), any(String.class))).thenReturn(
//                persons);
//        MvcResult mvcResult = this.mockMvc.perform(
//                delete(url).param("firstname", "testPerson").param("lastname",
//                        "testPerson")).andExpect(status().isNoContent()).andReturn();
//
//
//    }


}
