package com.example.asteroids.repo;

import com.example.asteroids.dto.nasa.AsteroidsApiRespData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
public class AsteroidsRepo {

    //todo : storing data to persistent layer can be introduced . Please check file "Todo-DataStoringImplementaion"
    //for high level diagram check : asteroidsHld.jpg

    //can keep this api key as env variable so that everyone don't have access to the key
    private static final String API_KEY = "ESZMAJvS7089iakAVNKDe7LAxXZkCstSQDriEMBw";

    //can be done in application properties as per profile
    private static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/feed?" +
            "start_date=%{startDate}&end_date=%{endDate}&api_key=" + API_KEY ;


    // this method calls the nasa api for the provided dates and map the results to required fields
    private AsteroidsApiRespData getAsteroids(LocalDate startDate, LocalDate endDate) {
        try {
            String startDateStr = getDateString(startDate);
            String endDateStr = getDateString(endDate);
            String urlStr = BASE_URL.replace("%{startDate}", startDateStr)
                    .replace("%{endDate}", endDateStr);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlStr))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper().readValue( response.body(), AsteroidsApiRespData.class);
        } catch (Exception ex) {
            //to-do : exception handling can be done based on response codes and exceptions
            System.out.println(ex);
        }
        return null;
    }

    // get asteroids data b/w given dates.
    // since nasa api works for 8 days, need to get data in parts and join the responses.
    public AsteroidsApiRespData getAsteroidsBetweenDates(LocalDate startDate, LocalDate endDate) {
        AsteroidsApiRespData response = new AsteroidsApiRespData();
        LocalDate firstDate = startDate;
        LocalDate secondDate = firstDate.plusDays(7);
        while (secondDate.isBefore(endDate) ) {
            response.addAsteroidData(getAsteroids(firstDate, secondDate));
            firstDate = secondDate.plusDays(1);
            secondDate = firstDate.plusDays(7);
        }

        if (secondDate.isAfter(endDate) || secondDate.equals(endDate)) {
            response.addAsteroidData(getAsteroids(firstDate, endDate));
        }

        return response;

    }

    //if format changes, we can just implement it further
    //currently, it works fine for "yyyy-MM-dd"
    private String getDateString(LocalDate date) {

        return date.toString();
    }
}
