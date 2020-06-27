package com.alfa.task3.service;

import com.alfa.task3.NotFoundException;
import com.alfa.task3.dao.BranchRepostory;
import com.alfa.task3.model.Branch;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BranchService {
    private final BranchRepostory branchRepostory;

    public Branch findById(Long id) {
        return branchRepostory.findById(id).orElseThrow(NotFoundException::new);
    }

    public BranchService(BranchRepostory branchRepostory) {
        this.branchRepostory = branchRepostory;
    }
}
