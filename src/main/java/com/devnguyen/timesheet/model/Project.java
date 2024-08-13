package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name = "customer_id")
    private int customerId;

    private String name;

    @Column(name = "project_code")
    private String projectCode;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private ProjectType projectType;

    // Enum for project types
    public enum ProjectType {
        Product,
        ODC,
        Training
    }

    // Getters and Setters
    // ...
}
