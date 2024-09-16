package com.cliengo.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RocketSource implements Serializable {
    private String rocketId;
    private String rocketName;
    private String description;
    @JsonProperty("flickr_images")
    private List<String> images;
}
