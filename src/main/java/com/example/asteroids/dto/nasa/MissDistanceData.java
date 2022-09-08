package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MissDistanceData {

    double astronomical;
    double lunar;
    double kilometers;
    double miles;
}
