package com.alfa.task3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "branches")
public class Branch {
    @Id
    @JsonProperty("id")
    @Column(name = "id", columnDefinition = "NUMERIC(19,2)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String title;

    private Double lon;

    private Double lat;

    private String address;
}
