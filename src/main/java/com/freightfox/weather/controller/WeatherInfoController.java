package com.freightfox.weather.controller;

import com.freightfox.weather.service.WeatherInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class WeatherInfoController {

    private WeatherInfoService weatherInfoService;
    @GetMapping("/api/weather/{pincode}")
    public String fetchWeatherByPincode(@PathVariable String pincode,@RequestParam LocalDate date)
    {
        return null;
    }
}
