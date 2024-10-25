package com.freightfox.weather.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "weather_info")
@Getter
@Setter
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "pincode")
    private String pincode;

    @Column(nullable = false, name = "latitude")
    private Double latitude;

    @Column(nullable = false, name = "longitude")
    private Double longitude;

    @Column(nullable = false, name = "date")
    private LocalDate date;

    @Column(nullable = false, name = "temperature")
    private Double temperature;

    @Column(nullable = false, name = "description")
    private String weatherDescription;
}
