package com.example.safetyNet.controller;


import com.example.safetyNet.model.FireStation;
import com.example.safetyNet.service.FireStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class FireStationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private FireStationService fireStationService;
//
//    private final String url = "/firestation";
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    // @Test
//    void shouldAddFireStationAndReturn201WhenValidInput() throws Exception {
//        //GIVEN
//        FireStation fireStation = new FireStation("1", "Test");
//
//        //WHEN
//        //when(fireStationServiceImpl.ajouter(any(FireStation.class))).thenReturn(
//        //Arrays.asList(fireStation));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(fireStation)))
//                .andExpect(status().isCreated()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        assertEquals(objectMapper.writeValueAsString(Arrays.asList(fireStation)), result);
//
//    }
//
//    // @Test
//    void shouldAddFireStationAndReturnWhenValidInput() throws Exception {
//        //GIVEN
//        FireStation fireStation = new FireStation();
//
//        //WHEN
//        when(fireStationService.ajouter(any(FireStation.class))).thenReturn(
//                Arrays.asList(fireStation));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        post(url).contentType("application/json")
//                                .content(objectMapper.writeValueAsString(fireStation)))
//                .andExpect(status().isBadRequest()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//        Pattern re = Pattern.compile("\\bstation|address\\b", Pattern.CASE_INSENSITIVE);
//        assertEquals(true, re.matcher(result).find());
//
//    }
//
//    // @Test
//    void shouldUpdateFireStationAndReturnWhenValidInput() throws Exception {
//        //GIVEN
//        FireStation fireStation = new FireStation("1", "1 Test");
//
//        //WHEN
//        when(fireStationService.update(any(String.class),
//                any(String.class))).thenReturn(
//                fireStation);
//        MvcResult mvcResult = this.mockMvc.perform(
//                        put(url).param("address", "1 Test").contentType(
//                                        "application" +
//                                                "/json")
//                                .content(objectMapper.writeValueAsString(fireStation)))
//                .andExpect(status().isOk()).andReturn();
//
//        //THEN
//        String result = mvcResult.getResponse().getContentAsString();
//
//        assertEquals(objectMapper.writeValueAsString(fireStation), result);
//
//    }
//
//    //@Test
//    void shouldDeleteFireStationAndReturnValidInput() throws Exception {
//
//        //WHEN
//        when(fireStationService.delete(any(String.class))).thenReturn(
//                Arrays.asList(new FireStation()));
//        MvcResult mvcResult = this.mockMvc.perform(
//                        delete(url).param("address", "1 Test")
//                )
//                .andExpect(status().isNoContent()).andReturn();
//
//    }


}
