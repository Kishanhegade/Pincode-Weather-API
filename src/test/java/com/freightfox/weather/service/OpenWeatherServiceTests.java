package com.freightfox.weather.service;

import com.freightfox.weather.dto.OpenWeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class OpenWeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenWeatherService openWeatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeather_ReturnsWeatherResponse() {
        String pincode = "411014";
        double lat = 18.5204;
        double lon = 73.8567;
        LocalDate date = LocalDate.now();

        OpenWeatherResponse mockResponse = new OpenWeatherResponse();

        OpenWeatherResponse.Main main = new OpenWeatherResponse.Main();
        main.setTemp(25.5);
        mockResponse.setMain(main);

        OpenWeatherResponse.Weather weather = new OpenWeatherResponse.Weather();
        weather.setDescription("clear sky");
        mockResponse.setWeather(List.of(weather));

        when(restTemplate.getForObject(anyString(), eq(OpenWeatherResponse.class))).thenReturn(mockResponse);

        OpenWeatherResponse actualResponse = openWeatherService.getWeather(pincode, lat, lon, date);

        assertEquals(25.5, actualResponse.getMain().getTemp());
        assertEquals("clear sky", actualResponse.getWeather().get(0).getDescription());
    }
}
