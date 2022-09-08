package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelativeVelocityData {
    @JsonProperty(value = "kilometers_per_second")
    double kilometersPerSec;

    @JsonProperty(value = "kilometers_per_hour")
    double kilometersPerHour;

    @JsonProperty(value = "miles_per_hour")
    double milesPerHour;
}
