package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExternalIntegrationControllerTest extends BaseIntegrationTest {

    @Test
    public void getDataShouldFailTillIntegrated() throws Exception {
        mockMvc.perform(get("/external/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception").value(containsString("ResourceAccessException")))
                .andExpect(jsonPath("$.message").value(containsString("I/O error")));
    }
}