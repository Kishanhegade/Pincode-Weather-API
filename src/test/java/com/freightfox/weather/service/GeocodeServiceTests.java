package com.freightfox.weather.service;

import com.freightfox.weather.dto.GeocodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;


public class GeocodeServiceTests {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeocodeService geocodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetLatLngFromPincode_ReturnsLatLng()
    {
        String pincode = "577432";

        GeocodeResponse mockResponse = new GeocodeResponse();
        mockResponse.setLat(14.0925);
        mockResponse.setLon(75.0658);

        when(restTemplate.getForObject(anyString(), eq(GeocodeResponse.class))).thenReturn(mockResponse);

        GeocodeResponse actualResponse = geocodeService.getLatLngFromPincode("577432");
        assertEquals(14.0925,actualResponse.getLat());
        assertEquals(75.0658,actualResponse.getLon());
    }

    @Test
    void testGetLatLngFromPincode_ThrowsExceptionForInvalidPincode() {
        String invalidPincode = "999999";

        when(restTemplate.getForObject(anyString(), eq(GeocodeResponse.class)))
                .thenThrow(new HttpClientErrorException(NOT_FOUND));

        assertThrows(HttpClientErrorException.class, () -> geocodeService.getLatLngFromPincode(invalidPincode));
    }
}
