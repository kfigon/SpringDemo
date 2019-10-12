package com.example.demo.component;

import com.example.demo.entity.SubTask;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskDescription;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    private TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initData();
    }

    public void initData() {
        Task t1 = Task.builder()
                .title("bare task")
                .build();

        Task t2 = Task.builder()
                .title("task with description")
                .taskDescription(TaskDescription.builder()
                        .description("important description")
                        .build())
                .build();
        t2.getTaskDescription().setTask(t2);

        Task t3 = Task.builder()
                .title("subtasked task")
                .subTasks(Arrays.asList(
                        createSubTask("first"),
                        createSubTask("second")
                ))
                .build();
        t3.getSubTasks().stream()
            .forEach(subTask -> {
                subTask.setTask(t3);
            });

        taskService.createTask(t1);
        taskService.createTask(t2);
        taskService.createTask(t3);
    }

    private SubTask createSubTask(String desc) {
        return SubTask.builder()
                .text(desc).build();
    }
}
