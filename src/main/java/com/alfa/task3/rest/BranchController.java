package com.alfa.task3.rest;

import com.alfa.task3.BranchOutDto;
import com.alfa.task3.model.Branch;
import com.alfa.task3.service.BranchService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public BranchOutDto findClosest(@RequestParam("lat") String lat, @RequestParam("lon") String lon) {
        System.out.println("Controller used");
        return branchService.findByCoords(lat, lon);
    }
}
