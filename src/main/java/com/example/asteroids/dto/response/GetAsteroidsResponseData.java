package com.example.asteroids.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAsteroidsResponseData {

    List<AsteroidRespData> asteroids;
}
