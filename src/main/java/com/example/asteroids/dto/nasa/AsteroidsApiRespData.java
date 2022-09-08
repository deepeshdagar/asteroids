package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsteroidsApiRespData {

    @JsonProperty(value = "near_earth_objects")
    Map<String, List<AsteroidsData>> nearEarthObjects = new HashMap<>();

    public void addAsteroidData(AsteroidsApiRespData asteroidApiResp){
        if (asteroidApiResp == null) {
            return;
        }
        for (Map.Entry<String, List<AsteroidsData>> entry : asteroidApiResp.getNearEarthObjects().entrySet()){
            if (!nearEarthObjects.containsKey(entry.getKey())) {
                nearEarthObjects.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
