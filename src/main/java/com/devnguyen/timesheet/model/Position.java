package com.devnguyen.timesheet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position_id;

    private String name;
    private String shortname;
    private String code;

    public Position() {}

    //Constructor



}
