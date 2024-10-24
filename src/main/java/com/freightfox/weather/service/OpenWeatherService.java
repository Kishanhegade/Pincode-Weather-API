package com.freightfox.weather.service;

import com.freightfox.weather.dto.OpenWeatherResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class OpenWeatherService {
    @Value("${OPENWEATHER_API_KEY}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public OpenWeatherResponse getWeather(String pincode, double lat,double lon, LocalDate date) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s",
                lat, lon, apiKey
        );

        return restTemplate.getForObject(url, OpenWeatherResponse.class);

    }
}
