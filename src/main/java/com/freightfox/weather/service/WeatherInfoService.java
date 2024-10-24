package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import com.freightfox.weather.dto.OpenWeatherResponse;
import com.freightfox.weather.dto.WeatherInfoResponse;
import com.freightfox.weather.mapper.WeatherInfoMapper;
import com.freightfox.weather.model.WeatherInfo;
import com.freightfox.weather.repository.WeatherInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherInfoService {

    private WeatherInfoRepository weatherInfoRepo;
    private OpenWeatherService openWeatherService;
    private GeocodeService geocodeService;
    private WeatherInfoMapper weatherInfoMapper;

    public WeatherInfoResponse fetchWeatherByPincode(String pincode, LocalDate date) {
        Optional<WeatherInfo> existingWeather = weatherInfoRepo.findByPincodeAndDate(pincode, date);
        if (existingWeather.isPresent()) {
            return weatherInfoMapper.mapToWeatherInfoResponse(existingWeather.get());
        } else {
            WeatherInfo weatherInfo = getWeatherForPincode(pincode,date);
            weatherInfoRepo.save(weatherInfo);
            return weatherInfoMapper.mapToWeatherInfoResponse(weatherInfo);
        }
    }

    public WeatherInfo getWeatherForPincode(String pincode, LocalDate date) {
        GeocodeResponse geocodeResponse = geocodeService.getLatLngFromPincode(pincode);
        OpenWeatherResponse openWeatherResponse = openWeatherService.getWeather(pincode,geocodeResponse.getLat(),geocodeResponse.getLon() , date);
        return weatherInfoMapper.mapToWeatherInfo(openWeatherResponse,geocodeResponse,pincode,date);
    }
}
