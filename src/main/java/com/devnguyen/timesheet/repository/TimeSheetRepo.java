package com.devnguyen.timesheet.repository;

import com.devnguyen.timesheet.model.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer> {

}
