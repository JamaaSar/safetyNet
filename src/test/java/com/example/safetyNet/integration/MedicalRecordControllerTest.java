package com.example.safetyNet.controller;


import com.example.safetyNet.dto.UpdateMedicalRecordDTO;
import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.service.MedicalRecordService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MedicalRecordController.class)
@AutoConfigureMockMvc(addFilters = false)
class MedicalRecordControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private MedicalRecordService medicalRecordService;
//
//    private final String url = "/medicalRecord";
//    @Autowired
//    private ObjectMapper objectMapper;
//    private MedicalRecord medicalRecord = new MedicalRecord();
//    private List<String> medications = new ArrayList<>();
//    private List<String> allergies = new ArrayList<>();
//
//    @BeforeEach
//    public void setUp() {
//        medications.add("med1");
//        medications.add("med2");
//        medications.add("med3");
//
//        allergies.add("allergie1");
//        allergies.add("allergie2");
//        allergies.add("allergie3");
//
//        medicalRecord.setLastName("LastName1");
//        medicalRecord.setFirstName("FirstName1");
//        medicalRecord.setBirthdate("26/04/1983");
//        medicalRecord.setMedications(medications);
//        medicalRecord.setAllergies(allergies);
//    }
//
//    @Test
//    void shouldAddFireStationAndReturn201WhenValidInput() throws Exception {
//        //GIVEN
//
//
//        //WHEN
//        when(medicalRecordService.ajouter(any(MedicalRecord.class))).thenReturn(
//                Arrays.asList(medicalRecord));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(medicalRecord)))
//                .andExpect(status().isCreated()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(medicalRecord)),
//                result);
//
//    }
//
//    @Test
//    void shouldAddFireStationAndReturnWhenValidInput() throws Exception {
//        //GIVEN
//
//        //WHEN
//        when(medicalRecordService.ajouter(any(MedicalRecord.class))).thenReturn(
//                Arrays.asList(new MedicalRecord()));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(new MedicalRecord())))
//                .andExpect(status().isBadRequest()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        Pattern re =
//                Pattern.compile("\\bmedications|allergies\\b", Pattern.CASE_INSENSITIVE);
//        assertEquals(true, re.matcher(result).find());
//
//    }
//
//    @Test
//    void shouldUpdateFireStationAndReturn200WhenValidInput() throws Exception {
//        //GIVEN
//
//        //WHEN
//        when(medicalRecordService.update(any(String.class), any(String.class),
//                any(UpdateMedicalRecordDTO.class))).thenReturn(
//                medicalRecord);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        put(url).param("firstname", "firstname").param("lastname", "lastname")
//                                .contentType(
//                                        "application" +
//                                                "/json")
//                                .content(objectMapper.writeValueAsString(medicalRecord)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(medicalRecord), result);
//
//    }
//
//    @Test
//    void shouldUpdateFireStationAndReturnValidInput() throws Exception {
//        FireStation fireStation = new FireStation();
//
//        //WHEN
//        when(medicalRecordService.update(any(String.class), any(String.class),
//                any(UpdateMedicalRecordDTO.class))).thenReturn(
//                medicalRecord);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        put(url).param("firstname", "firstname").param("lastname", "lastname")
//                                .contentType("application" + "/json")
//                                .content(objectMapper.writeValueAsString(medicalRecord)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(medicalRecord), result);
//
//    }
//
//    @Test
//    void shouldMedicalRecordAndReturnValidInput() throws Exception {
//
//        //WHEN
//        when(medicalRecordService.delete(any(String.class),
//                any(String.class))).thenReturn(
//                Arrays.asList(medicalRecord));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        delete(url).param("firstname", "firstname").param("lastname",
//                                "lastname")
//                )
//                .andExpect(status().isNoContent()).andReturn();
//
//    }


}
