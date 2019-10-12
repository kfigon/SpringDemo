package com.example.demo.service;

import com.example.demo.entity.SubTask;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskDescription;
import com.example.demo.repository.SubTaskRepository;
import com.example.demo.repository.TaskDescriptionRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private TaskDescriptionRepository taskDescriptionRepository;

    @Transactional
    public Task createTask(Task task) {
        createTaskDescription(task);
        createSubTasks(task);

        return taskRepository.save(task);
    }

    private void createSubTasks(Task task) {
        List<SubTask> subTasks = task.getSubTasks();
        if (!CollectionUtils.isEmpty(subTasks)) {
            subTaskRepository.saveAll(subTasks);
            task.setSubTasks(subTasks);
        }
    }

    private void createTaskDescription(Task task) {
        TaskDescription taskDescription = task.getTaskDescription();
        if (taskDescription != null) {
            taskDescriptionRepository.save(taskDescription);
            task.setTaskDescription(taskDescription);
        }
    }

    public Optional<Task> getTask(Integer id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> getTaskWithDescription(Integer id) {
        return taskRepository.findWithTaskDescription(id);
    }

    @Transactional
    public List<SubTask> getSubtasksForTask(Integer taskId) {
        Optional<Task> task = getTask(taskId);

        return task.map(subTaskRepository::findSubTasksByTask)
                .orElse(Collections.emptyList());
    }

    @Transactional
    public void deleteTask(Integer id) {
        Optional<Task> taskOpt = getTask(id);

        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();

            deleteTaskDescription(task);
            deleteSubTasks(task);
            taskRepository.delete(task);
        }
    }

    private void deleteTaskDescription(Task task) {
        TaskDescription taskDescription = task.getTaskDescription();
        if (taskDescription != null) {
            taskDescriptionRepository.delete(taskDescription);
        }
    }

    private void deleteSubTasks(Task task) {
        List<SubTask> subTasks = task.getSubTasks();
        if (!CollectionUtils.isEmpty(subTasks)) {
            subTaskRepository.deleteAll(subTasks);
        }
    }

    public List<Task> getAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
