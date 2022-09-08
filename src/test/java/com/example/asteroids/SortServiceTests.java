package com.example.asteroids;

import com.example.asteroids.dto.nasa.AsteroidsApiRespData;
import com.example.asteroids.dto.nasa.AsteroidsData;
import com.example.asteroids.enums.AsteroidPropertyEnum;
import com.example.asteroids.service.SortAsteroidsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class SortServiceTests {

    SortAsteroidsService sortAsteroidsService = new SortAsteroidsService();

    @Test
    void sortByDistanceTest() throws IOException {
        AsteroidsApiRespData asteroidsApiRespData = Utility.getFileContent("asteroidsData");

        List<AsteroidsData> sortedList = sortAsteroidsService.sortData(asteroidsApiRespData, 2, AsteroidPropertyEnum.DISTANCE, "ASC");

        assertNotNull(sortedList);
        assertEquals(2, sortedList.size());
        assertEquals("1", sortedList.get(0).getId());
        assertEquals("2", sortedList.get(1).getId());

        //todo :  further extend for all other cases.

    }
}
