package com.freightfox.weather.service;

import com.freightfox.weather.repository.WeatherInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherInfoService {
    private WeatherInfoRepository weatherInfoRepo;
}
