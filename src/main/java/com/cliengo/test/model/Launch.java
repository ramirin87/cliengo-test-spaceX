package com.cliengo.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties
public class Launch implements Serializable {
    private int flightNumber;
    private String missionName;
    private RocketShow rocket;
    private List<Payload> payloads;

    public Launch(LaunchSource source, List<Payload> payloads, RocketShow rocket) {
        this.flightNumber = source.getFlightNumber();
        this.missionName = source.getMissionName();
        this.rocket = rocket;
        this.payloads = payloads;
    }
}
