package com.devnguyen.timesheet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_types")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private int leaveTypeId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "num_days", nullable = false)
    private int numDays;

    // Constructors
    public LeaveType() {
    }

    public LeaveType(String name, int numDays) {
        this.name = name;
        this.numDays = numDays;
    }

    // Getters and Setters
    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
}
