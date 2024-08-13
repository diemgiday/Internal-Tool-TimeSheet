package com.devnguyen.timesheet.service;

import com.devnguyen.timesheet.model.Branch;
import com.devnguyen.timesheet.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public void updateBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public Branch findBranchById(int id) {
        return branchRepository.findById(id).orElse(null);
    }

    public void addBranch(Branch branch) {
        branchRepository.save(branch);
    }

    public void deleteBranch(@PathVariable int id) {
        branchRepository.deleteById(id);
    }
}
