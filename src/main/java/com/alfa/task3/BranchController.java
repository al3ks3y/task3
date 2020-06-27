package com.alfa.task3;

import com.alfa.task3.dao.BranchRepostory;
import com.alfa.task3.model.Branch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("branches")
public class BranchController {
    private final BranchRepostory branchRepostory;

    public BranchController(BranchRepostory branchRepostory) {
        this.branchRepostory = branchRepostory;
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable Long id) {
        return branchRepostory.findById(id).orElseThrow(NotFoundException::new);
    }
}
