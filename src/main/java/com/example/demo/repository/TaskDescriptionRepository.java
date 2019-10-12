package com.example.demo.repository;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDescriptionRepository extends CrudRepository<TaskDescription, Integer> {
}
