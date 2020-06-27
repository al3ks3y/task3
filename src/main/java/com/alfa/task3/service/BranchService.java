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
        System.out.println(String.format("finding with coords %s, %s ", lat, lon));
        List<Branch> branches = branchRepostory.findAll();
        Double lat1 = Double.parseDouble(lat);
        Double lon1 = Double.parseDouble(lon);
        final AtomicLong result = new AtomicLong(-1);
        AtomicLong closestBranchIndex = new AtomicLong();
        branches.forEach(b -> {
            Double distance = distance(b.getLat(), b.getLon(), lat1, lon1);
            if (result.get() < 0) result.set(distance.longValue());
            if (result.get() < distance) {
                result.set(distance.intValue());
                closestBranchIndex.set(b.getId());
            }
        });
        Branch closest = findById(closestBranchIndex.get());
        System.out.println("Distance is: " + result);
        return new BranchOutDto(closest.getId(), closest.getTitle(), closest.getLon(), closest.getLat(), closest.getAddress(), result.longValue());
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
