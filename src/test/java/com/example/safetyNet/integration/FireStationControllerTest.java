package com.example.safetyNet.integration;


import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.repository.FireStationRepository;
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
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FireStationRepository fireStationRepository;
    private final String url = "/firestation";
    @Autowired
    private ObjectMapper objectMapper;
    private FireStation fireStation = new FireStation();
    private List<FireStation> fireStations = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        fireStation = new FireStation();
        fireStation.setAddress("1 Test");
        fireStation.setStation("1");
        fireStations = fireStationRepository.getFireStationsList();
    }

    @Test
    void shouldReturnFireStation() throws Exception {
        this.mockMvc.perform(
                        get("/firestation/address").param("address", "1509 Culver St")
                                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldAddFireStationAndReturn201WhenValidInput() throws Exception {
        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    void shouldAddFireStationAndReturnWhenValidInput() throws Exception {
        FireStation fireStation = new FireStation();
        this.mockMvc.perform(
                        post(url).contentType("application/json")
                                .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isBadRequest()).andReturn();


    }

    @Test
    void shouldUpdateFireStationAndReturnWhenValidInput() throws Exception {
        fireStations.add(fireStation);
        this.mockMvc.perform(
                        put(url).param("address", "1 Test").contentType(
                                        "application" +
                                                "/json")
                                .content(objectMapper.writeValueAsString(fireStation)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void shouldDeleteFireStationAndReturnValidInput() throws Exception {
        fireStations.add(fireStation);
        this.mockMvc.perform(
                        delete(url).param("address", "1 Test")
                )
                .andExpect(status().isNoContent()).andReturn();

    }


}
