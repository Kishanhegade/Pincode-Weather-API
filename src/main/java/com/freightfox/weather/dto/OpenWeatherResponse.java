package com.freightfox.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenWeatherResponse {
    private Main main;
    private List<Weather> weather;


    @Getter
    @Setter
    public static class Main {
        private double temp;
    }

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }
}
