package com.example.asteroids.service;

import com.example.asteroids.dto.nasa.AsteroidsApiRespData;
import com.example.asteroids.dto.nasa.AsteroidsData;
import com.example.asteroids.dto.response.AsteroidRespData;
import com.example.asteroids.dto.response.GetAsteroidsResponseData;
import com.example.asteroids.enums.AsteroidPropertyEnum;
import com.example.asteroids.repo.AsteroidsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.asteroids.utility.ConversionUtil.convertApiAsteroidDataToResp;
import static com.example.asteroids.utility.ConversionUtil.convertListToResponse;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;


@Service
public class AsteroidService {

    @Autowired
    AsteroidsRepo asteroidsRepo;

    @Autowired
    SortAsteroidsService sortAsteroidsService;


    // get asteroids response b/w provided dates
    // param size : total records in response. default is 10
    // sortBy : property by which we need to get the data, options : closest to earth, velocity ( speed), size. default is distance.
    // sortOrder : whether the results required in asc or desc order
    public GetAsteroidsResponseData listAsteroids(LocalDate startDate, LocalDate endDate,
                                                  int size, AsteroidPropertyEnum sortBy,
                                                  String sortOrder) {

        //todo : validation on input request
        //1. end date cannot be smaller than endDate
        //2. no future dates
        //3. sortOrder is either asc or desc and no invalid values. can have the default if any invalid


        //get the response b/w dates
        AsteroidsApiRespData respData = asteroidsRepo.getAsteroidsBetweenDates(startDate, endDate);

        //sort the data based on input
        List list = sortAsteroidsService.sortData(respData, size, sortBy, sortOrder);

        //convert to desirable response
        return convertListToResponse(list);
    }

    // get smallest/largest, fastest/slowest, closest/farthest asteroid in a given year
    // param : year (input mandatory year)
    // sortBy : property by which the asteroid is required. Options : distance, velocity, size. Defualt is size
    // sortOrder : sort order,asc, desc . Default is desc
    public AsteroidRespData getAsteroid(String year, AsteroidPropertyEnum sortBy, String sortOrder) {

        //todo: validation on input request
        //1. valid year
        //2. not future year
        //3. sortOrder is either asc or desc and no invalid values. can have the default if any invalid

        year = year + "-01-01";
        LocalDate startDate = LocalDate.parse(year);

        //get the data for the whole year
        AsteroidsApiRespData respData = asteroidsRepo.getAsteroidsBetweenDates(startDate, startDate.with(lastDayOfYear()));
        //sort the data as per the size and order
        List<AsteroidsData> list = sortAsteroidsService.sortData(respData, 1, sortBy, sortOrder);
        //convert to desirable response
        return convertApiAsteroidDataToResp(list.get(0));

    }
}
