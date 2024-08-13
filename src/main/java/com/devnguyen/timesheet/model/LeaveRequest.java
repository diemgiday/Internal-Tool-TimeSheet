package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_request_id")
    private int leaveRequestId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "leave_type_id")
    private int leaveTypeId;

    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "note")
    private String note;

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    // Constructors
    public LeaveRequest() {
    }

    public LeaveRequest(int userId, int leaveTypeId, LocalDate requestDate, Status status, String note) {
        this.userId = userId;
        this.leaveTypeId = leaveTypeId;
        this.requestDate = requestDate;
        this.status = status;
        this.note = note;
    }

    // Getters and Setters
    public int getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(int leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
