package com.freightfox.weather.exceptionhandler;

import com.freightfox.weather.utility.AppResponseBuilder;
import com.freightfox.weather.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
@AllArgsConstructor
public class WeatherInfoExceptionHandler {
    private final AppResponseBuilder builder;

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorStructure<String>> handleHttpClientError(HttpClientErrorException ex) {
        return builder.error(HttpStatus.BAD_REQUEST, ex.getMessage(), "External API returned client error");
    }


    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorStructure<String>> handleHttpServerError(HttpServerErrorException ex) {
        return builder.error(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), "External API is unavailable");
    }


    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorStructure<String>> handleResourceAccessError(ResourceAccessException ex) {
        return builder.error(HttpStatus.GATEWAY_TIMEOUT, ex.getMessage(), "Network error or timeout occurred");
    }
}
