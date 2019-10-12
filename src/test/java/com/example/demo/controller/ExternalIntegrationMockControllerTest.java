package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWireMock(port = 9999)
public class ExternalIntegrationMockControllerTest extends BaseIntegrationTest {

    @Test
    public void getDataMock() throws Exception {
        mockMvc.perform(get("/external/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("some name"))
                .andExpect(jsonPath("$.value").value(123));
    }
}