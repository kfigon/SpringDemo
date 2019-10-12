package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query("from Task t " +
            " left join fetch TaskDescription td " +
            "where t.id = ?1")
    Optional<Task> findWithTaskDescription(Integer id);
}
