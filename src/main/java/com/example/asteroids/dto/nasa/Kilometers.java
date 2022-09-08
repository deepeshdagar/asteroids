package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Kilometers {

    @JsonProperty(value = "estimated_diameter_min")
    double estimatedDiameterMin;

    @JsonProperty(value = "estimated_diameter_max")
    double estimatedDiameterMax;


}
