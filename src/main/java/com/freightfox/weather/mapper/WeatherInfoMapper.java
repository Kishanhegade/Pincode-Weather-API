package com.freightfox.weather.mapper;

import com.freightfox.weather.dto.GeocodeResponse;
import com.freightfox.weather.dto.OpenWeatherResponse;
import com.freightfox.weather.dto.WeatherInfoResponse;
import com.freightfox.weather.model.WeatherInfo;

import java.time.LocalDate;

public class WeatherInfoMapper {

    public WeatherInfo mapToWeatherInfo(OpenWeatherResponse openWeatherResponse, GeocodeResponse geocodeResponse, String pincode, LocalDate date) {
        WeatherInfo weatherInfo = new WeatherInfo();

        weatherInfo.setPincode(pincode);
        weatherInfo.setLatitude(geocodeResponse.getLat());
        weatherInfo.setLongitude(geocodeResponse.getLon());
        weatherInfo.setTemperature(openWeatherResponse.getMain().getTemp());
        weatherInfo.setDate(date);
        weatherInfo.setWeatherDescription(openWeatherResponse.getWeather().getFirst().getDescription());
        return weatherInfo;
    }

    public WeatherInfoResponse mapToWeatherInfoResponse(WeatherInfo weatherInfo) {
        WeatherInfoResponse weatherInfoResponse = new WeatherInfoResponse();
        weatherInfoResponse.setDate(weatherInfo.getDate());
        weatherInfoResponse.setLatitude(weatherInfo.getLatitude());
        weatherInfoResponse.setLongitude(weatherInfo.getLongitude());
        weatherInfoResponse.setTemperature(weatherInfo.getTemperature());
        weatherInfoResponse.setWeatherDescription(weatherInfo.getWeatherDescription());
        return weatherInfoResponse;
    }
}
