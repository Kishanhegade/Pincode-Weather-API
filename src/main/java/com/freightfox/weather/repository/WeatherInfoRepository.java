package com.freightfox.weather.repository;

import com.freightfox.weather.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo,Long> {
}
