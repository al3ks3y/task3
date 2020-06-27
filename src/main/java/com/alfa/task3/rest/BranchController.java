package com.alfa.task3.rest;

import com.alfa.task3.model.Branch;
import com.alfa.task3.service.BranchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable Long id) {
        return branchService.findById(id);
    }
}
