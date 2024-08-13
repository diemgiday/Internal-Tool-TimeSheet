package com.devnguyen.timesheet.controller.crud;

import com.devnguyen.timesheet.model.Branch;
import com.devnguyen.timesheet.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/all")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @PostMapping("/add")
    public void addBranch(@RequestBody Branch branch) {
        branchService.addBranch(branch);
    }

    @PutMapping("/update")
    public void updateBranch(@RequestBody Branch branch) {
        branchService.updateBranch(branch);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable int id) {
        branchService.deleteBranch(id);
    }

}
