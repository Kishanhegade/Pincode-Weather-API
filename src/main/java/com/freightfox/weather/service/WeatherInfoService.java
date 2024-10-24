package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import com.freightfox.weather.dto.OpenWeatherResponse;
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

    public WeatherInfo fetchWeatherByPincode(String pincode, LocalDate date) {
        Optional<WeatherInfo> existingWeather = weatherInfoRepo.findByPincodeAndDate(pincode, date);
        if (existingWeather.isPresent()) {
            return existingWeather.get();
        } else {
            WeatherInfo weatherInfo = getWeatherForPincode(pincode,date);
            weatherInfoRepo.save(weatherInfo);
            return weatherInfo;
        }
    }

    public WeatherInfo getWeatherForPincode(String pincode, LocalDate date) {
        GeocodeResponse geocodeResponse = geocodeService.getLatLngFromPincode(pincode);
        OpenWeatherResponse openWeatherResponse = openWeatherService.getWeather(pincode,geocodeResponse.getLat(),geocodeResponse.getLon() , date);

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPincode(pincode);
        weatherInfo.setLatitude(geocodeResponse.getLat());
        weatherInfo.setLongitude(geocodeResponse.getLon());
        weatherInfo.setTemperature(openWeatherResponse.getMain().getTemp());
        weatherInfo.setDate(date);
        weatherInfo.setWeatherDescription(openWeatherResponse.getWeather().getFirst().getDescription());
        return weatherInfo;
    }
}
