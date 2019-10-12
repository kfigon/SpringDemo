package com.example.demo.repository;

import com.example.demo.entity.SubTask;
import com.example.demo.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends CrudRepository<SubTask, Integer> {
    List<SubTask> findSubTasksByTask(Task task);
}
