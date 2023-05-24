package com.example.safetyNet.integration;


import com.example.safetyNet.model.MedicalRecord;
import com.example.safetyNet.repository.MedicalRecordRepository;
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
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private final String url = "/medicalRecord";
    private MedicalRecord medicalRecord = new MedicalRecord();
    private List<MedicalRecord> medicalRecordList = new ArrayList<>();
    private List<String> medications = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        medications.add("med1");
        medications.add("med2");
        medications.add("med3");

        allergies.add("allergie1");
        allergies.add("allergie2");
        allergies.add("allergie3");

        medicalRecord.setLastName("LastName");
        medicalRecord.setFirstName("FirstName");
        medicalRecord.setBirthdate("26/04/1983");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        medicalRecordList = medicalRecordRepository.getMedicalRecordsList();

    }

    @Test
    void shouldReturnMedicalRecord() throws Exception {
        this.mockMvc.perform(
                        get(url).param("firstname", "Foster").param("lastname",
                                        "Shepard")
                                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldAddFireStationAndReturn201WhenValidInput() throws Exception {

        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isCreated()).andReturn();

    }

    @Test
    void shouldAddFireStationAndReturnWhenValidInput() throws Exception {

        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(new MedicalRecord())))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    void shouldUpdateMedicalRecordAndReturn200WhenValidInput() throws Exception {
        medicalRecordList.add(medicalRecord);
        this.mockMvc.perform(
                        put(url).param("firstname", "FirstName").param("lastname", "LastName")
                                .contentType("application" + "/json")
                                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void shouldDeleteMedicalRecordAndReturnValidInput() throws Exception {
        medicalRecordList.add(medicalRecord);
        this.mockMvc.perform(
                        delete(url).param("firstname", "FirstName").param("lastname",
                                "LastName")
                )
                .andExpect(status().isNoContent()).andReturn();

    }

}
