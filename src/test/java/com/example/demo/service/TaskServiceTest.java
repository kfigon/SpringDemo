package com.example.demo.service;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.entity.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TaskServiceTest extends BaseIntegrationTest {

    @Autowired
    private TaskService service;

    @Test
    public void createTask() {
    }

    @Test
    public void getTask() {
        Optional<Task> taskOpt = service.getTask(1);
        assertTrue(taskOpt.isPresent());

        Task task = taskOpt.get();
        assertNull(task.getTaskDescription());

        //is null, but throws an exception
//        Throwable thrown = catchThrowable(() -> task.getSubTasks());
//        assertThat(thrown).isInstanceOf(LazyInitializationException.class);

        assertEquals("bare task", task.getTitle());
    }
}