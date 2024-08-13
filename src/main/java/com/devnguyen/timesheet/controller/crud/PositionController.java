package com.devnguyen.timesheet.controller.crud;


import com.devnguyen.timesheet.model.Position;
import com.devnguyen.timesheet.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("positions")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/all")
    public List<Position> getALLPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping("/add")
    public void addPosition(@RequestBody Position pos) {
        positionService.addPosition(pos);
    }

    @DeleteMapping("/delete/{positionId}")
    public void deletePosition(@PathVariable int positionId) {
         positionService.deletePosition(positionId);
    }
}
