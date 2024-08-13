package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "project_users")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectUserId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // Getters and Setters
}
