package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer integer;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Task task;
}
