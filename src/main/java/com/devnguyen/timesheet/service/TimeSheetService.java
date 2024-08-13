package com.devnguyen.timesheet.service;

import com.devnguyen.timesheet.model.TimeSheet;
import com.devnguyen.timesheet.repository.TimeSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSheetService {

    @Autowired
    private TimeSheetRepo timeSheetRepo;


    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetRepo.findAll();
    }

    public void addTimeSheet(TimeSheet timeSheet) {
        timeSheetRepo.save(timeSheet);
    }

    public void updateTimeSheet(TimeSheet timeSheet) {
        TimeSheet newTimeSheet = timeSheetRepo.save(timeSheet);
        timeSheetRepo.save(newTimeSheet);
    }

    public void deleteTimeSheetById(int id) {
        timeSheetRepo.deleteById(id);
    }
}
