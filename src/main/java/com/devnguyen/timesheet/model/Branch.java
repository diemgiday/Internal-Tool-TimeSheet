package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity(name= "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branch_id;

    private String name;
    private String displayName;
    private String code;
    private String workingTime;

    public Branch() {}


}
