package com.alfa.task3.dao;

import com.alfa.task3.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepostory extends JpaRepository<Branch, Long> {
}
