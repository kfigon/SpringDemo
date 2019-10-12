package com.example.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InvalidClassException;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTask() throws Exception {
        mockMvc.perform(get("/task/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title").value("bare task"))
                .andExpect(jsonPath("$.subTasks").value(nullValue()))
                .andExpect(jsonPath("$.taskDescription").value(nullValue()));
    }

    @Test
    public void getTask3() throws Exception {
        mockMvc.perform(get("/task/3"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title").value("task with description"))
                .andExpect(jsonPath("$.subTasks").isEmpty())
                .andExpect(jsonPath("$.taskDescription").value(nullValue()));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/task/all"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*]title").exists())
                .andExpect(jsonPath("$.[*]subTasks").value(everyItem(nullValue())))
                .andExpect(jsonPath("$.[*]taskDescription").value(everyItem(nullValue())));
    }

    @Test
    public void pathWithException() throws Exception {
        mockMvc.perform(get("/task/crash"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.path").value("/task/crash"))
                .andExpect(jsonPath("$.timestamp").isNumber())
                .andExpect(jsonPath("$.message").value("my exception!"))
                .andExpect(jsonPath("$.exception").value(InvalidClassException.class.getName()));
    }

    @Test
    @DirtiesContext
    public void deleteTask() throws Exception {
        mockMvc.perform(delete("/task/1"))
                .andExpect(status().is2xxSuccessful());
    }
}