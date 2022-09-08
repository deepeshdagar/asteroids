package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimatedDiameterData {

    @JsonProperty(value = "kilometers")
    Kilometers kilometers;
}

