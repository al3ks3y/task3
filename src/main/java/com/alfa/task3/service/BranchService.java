package com.alfa.task3.service;

import com.alfa.task3.BranchOutDto;
import com.alfa.task3.NotFoundException;
import com.alfa.task3.dao.BranchRepostory;
import com.alfa.task3.model.Branch;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional
public class BranchService {
    private final BranchRepostory branchRepostory;

    public BranchService(BranchRepostory branchRepostory) {
        this.branchRepostory = branchRepostory;
    }

    public Branch findById(Long id) {
        return branchRepostory.findById(id).orElseThrow(NotFoundException::new);
    }

    public BranchOutDto findByCoords(String lat, String lon) {
        final int R = 6371; // Радиус земли матушки
        List<Branch> branches = branchRepostory.findAll();
        Double lat1 = Double.parseDouble(lat);
        Double lon1 = Double.parseDouble(lon);
        final AtomicLong result = new AtomicLong(-1);
        AtomicLong closestBranchIndex = new AtomicLong();
        branches.forEach(b -> {
            Double latDistance = toRad(lat1 - b.getLat());
            Double lonDistance = toRad(lon1 - b.getLon());
            Double coef = Math.pow(Math.sin(latDistance / 2), 2) + Math.cos(toRad(lat1)) * Math.cos(b.getLat()) *
                    Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            Double c = 2 * Math.atan2(Math.sqrt(coef), Math.sqrt(1 - coef));
            Double distance = R * c;
            if (result.get() < 0) result.set(distance.longValue());
            if (result.get() < distance) {
                result.set(distance.intValue());
                closestBranchIndex.set(b.getId());
            }
        });
        Branch closest = findById(closestBranchIndex.get());
        return new BranchOutDto(closest.getId(), closest.getTitle(), closest.getLon(), closest.getLat(), closest.getAddress(), result.longValue());
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
