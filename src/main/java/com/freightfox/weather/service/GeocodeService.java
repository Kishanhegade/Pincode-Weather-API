package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service

public class GeocodeService {

    @Value("${OPENWEATHER_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeocodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GeocodeResponse getLatLngFromPincode(String pincode) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/geo/1.0/zip")
                .queryParam("zip", pincode+ "," + "IN")
                .queryParam("appid", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, GeocodeResponse.class);
    }

}
