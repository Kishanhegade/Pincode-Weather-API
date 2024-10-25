package com.freightfox.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeatherInfoResponse {
    private String pincode;
    private Double latitude;
    private Double longitude;
    private LocalDate date;
    private Double temperature;
    private String weatherDescription;
}
