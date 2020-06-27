package com.alfa.task3;

public class BranchOutDto {
    private Long id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;

    private Long distance;

    public BranchOutDto(Long id, String title, Double lon, Double lat, String address, Long distance) {
        this.id = id;
        this.title = title;
        this.lon = lon;
        this.lat = lat;
        this.address = address;
        this.distance = distance;
    }
}
