package com.example.asteroids.controller;

import com.example.asteroids.dto.response.AsteroidRespData;
import com.example.asteroids.dto.response.GetAsteroidsResponseData;
import com.example.asteroids.enums.AsteroidPropertyEnum;
import com.example.asteroids.service.AsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AsteroidController {

    @Autowired
    AsteroidService asteroidService;

    //default is : 10 closest asteroids to earth in a given date range
    //http://localhost:9000/list/asteroids?startDate=2015-01-01&endDate=2015-01-10&size=2&sortBy=VELOCITY&sortOrder=ASC
    @GetMapping("/list/asteroids")
    public GetAsteroidsResponseData listAsteroids(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                  @RequestParam (defaultValue = "10") int size,
                                                  @RequestParam (defaultValue = "DISTANCE") AsteroidPropertyEnum sortBy,
                                                  @RequestParam (defaultValue = "ASC") String sortOrder) {

        return asteroidService.listAsteroids(startDate, endDate, size, sortBy, sortOrder);
    }


    //default is largest asteroid in size/diameter in a given year.
    //http://localhost:9000/get/asteroid?year=2015
    @GetMapping("/get/asteroid")
    public AsteroidRespData getAsteroid(@RequestParam String year,
                                        @RequestParam (defaultValue = "SIZE") AsteroidPropertyEnum asteroidPropertyEnum,
                                        @RequestParam (defaultValue = "DESC") String order) {
        return asteroidService.getAsteroid(year, asteroidPropertyEnum, order);
    }



}
