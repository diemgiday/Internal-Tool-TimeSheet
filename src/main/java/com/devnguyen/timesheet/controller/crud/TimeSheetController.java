package com.devnguyen.timesheet.controller.crud;


import com.devnguyen.timesheet.model.TimeSheet;
import com.devnguyen.timesheet.service.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {

    @Autowired
    private TimeSheetService timeSheetService;

    @GetMapping("/all")
    public List<TimeSheet> getAllTimeSheets() {
        return timeSheetService.getAllTimeSheets();
    }

    @PostMapping("/add")
    public void addTimeSheet(@RequestBody TimeSheet timeSheet) {
        timeSheetService.addTimeSheet(timeSheet);
    }

    @PutMapping("/update")
    public void updateTimeSheet(@RequestBody TimeSheet timeSheet) {
        timeSheetService.updateTimeSheet(timeSheet);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTimeSheetById(@PathVariable int id) {
        timeSheetService.deleteTimeSheetById(id);
    }
}
