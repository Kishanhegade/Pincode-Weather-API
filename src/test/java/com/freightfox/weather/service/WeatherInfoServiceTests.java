package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import com.freightfox.weather.dto.OpenWeatherResponse;
import com.freightfox.weather.dto.WeatherInfoResponse;
import com.freightfox.weather.mapper.WeatherInfoMapper;
import com.freightfox.weather.model.WeatherInfo;
import com.freightfox.weather.repository.WeatherInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class WeatherInfoServiceTests {

    @Mock
    private WeatherInfoRepository weatherInfoRepo;

    @Mock
    private OpenWeatherService openWeatherService;

    @Mock
    private GeocodeService geocodeService;

    @Mock
    private WeatherInfoMapper weatherInfoMapper;

    @InjectMocks
    private WeatherInfoService weatherInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchWeatherByPincode_WhenWeatherDoesNotExist() {
        String pincode = "411014";
        LocalDate date = LocalDate.now();

        GeocodeResponse geocodeResponse = new GeocodeResponse();
        geocodeResponse.setLat(18.5204);
        geocodeResponse.setLon(73.8567);

        OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
        OpenWeatherResponse.Main main = new OpenWeatherResponse.Main();
        main.setTemp(25.5);
        openWeatherResponse.setMain(main);

        WeatherInfo weatherInfo = new WeatherInfo();
        WeatherInfoResponse expectedResponse = new WeatherInfoResponse();

        when(weatherInfoRepo.findByPincodeAndDate(eq(pincode), eq(date))).thenReturn(Optional.empty());
        when(geocodeService.getLatLngFromPincode(pincode)).thenReturn(geocodeResponse);
        when(openWeatherService.getWeather(eq(pincode), eq(geocodeResponse.getLat()), eq(geocodeResponse.getLon()), eq(date))).thenReturn(openWeatherResponse);
        when(weatherInfoMapper.mapToWeatherInfo(openWeatherResponse, geocodeResponse, pincode, date)).thenReturn(weatherInfo);
        when(weatherInfoMapper.mapToWeatherInfoResponse(weatherInfo)).thenReturn(expectedResponse);

        WeatherInfoResponse actualResponse = weatherInfoService.fetchWeatherByPincode(pincode, date);

        assertEquals(expectedResponse, actualResponse);
        verify(weatherInfoRepo).findByPincodeAndDate(pincode, date);
        verify(weatherInfoRepo).save(weatherInfo);
        verify(weatherInfoMapper).mapToWeatherInfo(openWeatherResponse, geocodeResponse, pincode, date);
        verify(weatherInfoMapper).mapToWeatherInfoResponse(weatherInfo);
    }

    @Test
    void testGetWeatherForPincode() {
        String pincode = "411014";
        LocalDate date = LocalDate.now();

        GeocodeResponse geocodeResponse = new GeocodeResponse();
        geocodeResponse.setLat(18.5204);
        geocodeResponse.setLon(73.8567);

        OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
        OpenWeatherResponse.Main main = new OpenWeatherResponse.Main();
        main.setTemp(25.5);
        openWeatherResponse.setMain(main);

        WeatherInfo expectedWeatherInfo = new WeatherInfo();

        when(geocodeService.getLatLngFromPincode(pincode)).thenReturn(geocodeResponse);
        when(openWeatherService.getWeather(eq(pincode), eq(geocodeResponse.getLat()), eq(geocodeResponse.getLon()), eq(date))).thenReturn(openWeatherResponse);
        when(weatherInfoMapper.mapToWeatherInfo(openWeatherResponse, geocodeResponse, pincode, date)).thenReturn(expectedWeatherInfo);

        WeatherInfo actualWeatherInfo = weatherInfoService.getWeatherForPincode(pincode, date);

        assertEquals(expectedWeatherInfo, actualWeatherInfo);
        verify(geocodeService).getLatLngFromPincode(pincode);
        verify(openWeatherService).getWeather(eq(pincode), eq(geocodeResponse.getLat()), eq(geocodeResponse.getLon()), eq(date));
        verify(weatherInfoMapper).mapToWeatherInfo(openWeatherResponse, geocodeResponse, pincode, date);
    }
}
