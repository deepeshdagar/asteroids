package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloseApproachData {

    @JsonProperty(value = "close_approach_date")
    String closeApproachDate;

    @JsonProperty(value = "miss_distance")
    MissDistanceData missDistanceData;

    @JsonProperty(value = "relative_velocity")
    RelativeVelocityData relativeVelocityData;
}
