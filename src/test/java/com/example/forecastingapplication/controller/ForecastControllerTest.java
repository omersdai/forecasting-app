package com.example.forecastingapplication.controller;

import com.example.forecastingapplication.model.WeatherForecast;
import com.example.forecastingapplication.service.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ForecastControllerTest {

    @Mock
    private ForecastService forecastService;

    @InjectMocks
    private ForecastController forecastController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeatherForecast_Success() {
        // Arrange
        String city = "London";
        WeatherForecast expectedForecast = WeatherForecast.builder().build();
        expectedForecast.setCityName(city);

        // Mocking the behavior of the service
        when(forecastService.requestWeatherForecast(city)).thenReturn(expectedForecast);

        // Act
        ResponseEntity<WeatherForecast> response = forecastController.getWeatherForecast(city);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedForecast, response.getBody());
    }

    @Test
    public void testGetWeatherForecast_Failure() {
        // Arrange
        String city = "NonexistentCity";

        // Mocking the behavior of the service
        when(forecastService.requestWeatherForecast(city)).thenReturn(null);

        // Act
        ResponseEntity<WeatherForecast> response = forecastController.getWeatherForecast(city);

        // Assert
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}