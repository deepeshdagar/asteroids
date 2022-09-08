package com.example.asteroids;

import com.example.asteroids.dto.nasa.AsteroidsApiRespData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;

public class Utility {

    public static AsteroidsApiRespData getFileContent(String fileName) throws IOException {
        FileReader fr=new FileReader("src/test/resources/sampleData/"+fileName);
        String content = getFileContent(fr);
        return new ObjectMapper().readValue( content, AsteroidsApiRespData.class);
    }

    private static String getFileContent(FileReader fr) throws IOException {
        int i;
        String content = "";
        while((i=fr.read())!=-1) {
            content +=(char)i;
        }
        fr.close();
        return content;
    }
}
