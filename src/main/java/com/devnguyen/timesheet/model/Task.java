package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "task")
    private Set<ProjectTask> tasks = new HashSet<>();

    // Getters and Setters
}
