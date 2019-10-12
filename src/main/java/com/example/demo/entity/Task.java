package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private TaskDescription taskDescription;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SubTask> subTasks;
}
