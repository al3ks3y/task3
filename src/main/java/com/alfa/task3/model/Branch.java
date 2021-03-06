package com.alfa.task3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "branches")
public class Branch {
    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    public String getAddress() {
        return address;
    }
}
