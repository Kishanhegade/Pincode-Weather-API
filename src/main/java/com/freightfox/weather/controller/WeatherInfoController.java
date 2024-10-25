package com.freightfox.weather.controller;

import com.freightfox.weather.dto.WeatherInfoResponse;
import com.freightfox.weather.service.WeatherInfoService;
import com.freightfox.weather.utility.AppResponseBuilder;
import com.freightfox.weather.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class WeatherInfoController {

    private WeatherInfoService weatherInfoService;
    private AppResponseBuilder builder;
    @GetMapping("/api/weather/{pincode}")
    public ResponseEntity<ResponseStructure<WeatherInfoResponse>> fetchWeatherByPincode(@PathVariable String pincode, @RequestParam LocalDate date)
    {
        WeatherInfoResponse weatherInfoResponse = weatherInfoService.fetchWeatherByPincode(pincode,date);
        return builder.success(HttpStatus.FOUND, "Weather Info found", weatherInfoResponse);
    }
}
