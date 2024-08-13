package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String code;
    private String name;
    private String address;

//    @OneToMany(mappedBy = "customer")
//    private List<Project> projects;

    // Getters and Setters
}
