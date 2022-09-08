package com.example.asteroids.utility;

import com.example.asteroids.dto.nasa.AsteroidsData;
import com.example.asteroids.dto.response.AsteroidRespData;
import com.example.asteroids.dto.response.GetAsteroidsResponseData;

import java.util.ArrayList;
import java.util.List;

public class ConversionUtil {

    public static GetAsteroidsResponseData convertListToResponse(List<AsteroidsData> list) {
        GetAsteroidsResponseData response = new GetAsteroidsResponseData();
        List<AsteroidRespData> asteroidsList = new ArrayList<>();
        for (AsteroidsData asteroidsData : list) {
            asteroidsList.add(convertApiAsteroidDataToResp(asteroidsData));
        }
        response.setAsteroids(asteroidsList);
        return response;
    }

    public static AsteroidRespData convertApiAsteroidDataToResp(AsteroidsData asteroidsData) {

        AsteroidRespData asteroidRespData = new AsteroidRespData();
        asteroidRespData.setId(asteroidsData.getId());
        asteroidRespData.setName(asteroidsData.getName());
        asteroidRespData.setCloseApproachData(asteroidsData.getCloseApproachDataList());
        asteroidRespData.setEstimatedDiameterData(asteroidsData.getEstimatedDiameterData());
        return asteroidRespData;

    }
}
