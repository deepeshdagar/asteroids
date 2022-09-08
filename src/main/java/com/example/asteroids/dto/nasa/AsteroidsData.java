package com.example.asteroids.dto.nasa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsteroidsData {

    String id;
    String name;
    @JsonProperty(value = "close_approach_data")
    List<CloseApproachData> closeApproachDataList;

    @JsonProperty(value = "estimated_diameter")
    EstimatedDiameterData estimatedDiameterData;

    @JsonIgnore
    CloseApproachData firstAsteroidByDistance;

    @JsonIgnore
    CloseApproachData firstAsteroidByVelocity;

    //above two fields required based on sorting properties.

    public void setCloseApproachDataList(List<CloseApproachData> closeApproachDataList) {
        this.closeApproachDataList = closeApproachDataList;
        this.firstAsteroidByDistance = closeApproachDataList.stream().min(Comparator.comparing(v -> v.missDistanceData.kilometers)).get();
        this.firstAsteroidByVelocity = closeApproachDataList.stream().min(Comparator.comparing(v -> v.relativeVelocityData.kilometersPerHour)).get();
    }

}
