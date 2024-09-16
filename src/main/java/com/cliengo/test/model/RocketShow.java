package com.cliengo.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RocketShow {
    private String rocketId;
    private String rocketName;
    private String description;
    private List<String> images;

    public RocketShow(RocketSource rocket) {
        this.rocketId = rocket.getRocketId();
        this.rocketName = rocket.getRocketName();
        this.description = rocket.getDescription();
        this.images = rocket.getImages();
    }
}

