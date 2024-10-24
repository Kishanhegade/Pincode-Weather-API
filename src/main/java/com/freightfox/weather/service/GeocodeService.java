package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import com.freightfox.weather.dto.LatLng;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class GeocodeService {

    @Value("${OPENWEATHER_API_KEY}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public LatLng getLatLngFromPincode(String pincode) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/geo/1.0/zip")
                .queryParam("zip", pincode+ "," + "IN")
                .queryParam("appid", apiKey)
                .toUriString();

        GeocodeResponse response = restTemplate.getForObject(url, GeocodeResponse.class);

        return new LatLng(response.getLat(), response.getLon());
    }

}
