package com.example.asteroids.service;

import com.example.asteroids.dto.nasa.AsteroidsApiRespData;
import com.example.asteroids.dto.nasa.AsteroidsData;
import com.example.asteroids.enums.AsteroidPropertyEnum;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortAsteroidsService {


    //this sorts data bases on sortBy options and sortOrder

    public List<AsteroidsData> sortData(AsteroidsApiRespData apiRespData, int requiredRecords,
                                        AsteroidPropertyEnum asteroidPropertyEnum, String sortOrder) {

        List<AsteroidsData> list = apiRespData.getNearEarthObjects().values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        switch (asteroidPropertyEnum) {
            case DISTANCE :
                sortByDistance(list);
                break;
            case SIZE:
                sortBySize(list);
                break;
            case VELOCITY:
                sortByVelocity(list);
                break;
            default:
                break;

            //can be extended for other properties of asteroid.
        }


        if ("DESC".equalsIgnoreCase(sortOrder)) {
            Collections.reverse(list);
        }
        list = list.stream().limit(requiredRecords).collect(Collectors.toList());

        return list;

    }

    //sorting methods based on properties
    private void sortBySize(List<AsteroidsData> list) {
        list.sort(Comparator.comparing(v -> v.getEstimatedDiameterData().getKilometers().getEstimatedDiameterMax()));
    }

    private void sortByDistance(List<AsteroidsData> list) {
         list.sort(Comparator.comparing(v -> v.getFirstAsteroidByDistance().getMissDistanceData().getKilometers()));
    }

    private void sortByVelocity(List<AsteroidsData> list) {
        list.sort(Comparator.comparing(v -> v.getFirstAsteroidByVelocity().getRelativeVelocityData().getKilometersPerHour()));
    }


}
