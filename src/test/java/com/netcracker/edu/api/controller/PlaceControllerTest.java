package com.netcracker.edu.api.controller;

import com.netcracker.edu.api.controller.util.AppUtility;
import com.netcracker.edu.api.controller.util.TestValidationUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
//@ActiveProfiles("test")
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPlaceByAdress() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/place"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String expectedResponse = AppUtility.getContentFromResourceFile("json/TestGetPlaceByid_response.json");
        String response = result.getResponse().getContentAsString();

        TestValidationUtility.validateResponse(result.getResponse(), expectedResponse);
    }
/*
    @Test
    public void findUserById() {
        Object result = reviewService.getByAuthorId(4, 0);
        Assertions.assertNotEquals(null, result);
    }*/
}
