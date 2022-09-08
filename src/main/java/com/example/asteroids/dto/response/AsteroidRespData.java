package com.example.asteroids.dto.response;

import com.example.asteroids.dto.nasa.CloseApproachData;
import com.example.asteroids.dto.nasa.EstimatedDiameterData;
import lombok.Data;

import java.util.List;

@Data
public class AsteroidRespData {

    String id;
    String name;
    List<CloseApproachData> closeApproachData;
    EstimatedDiameterData estimatedDiameterData;
}
