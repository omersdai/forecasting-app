package com.example.forecastingapplication.service.impl;

import com.example.forecastingapplication.model.WeatherForecast;
import com.example.forecastingapplication.open_weather.OpenWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ForecastServiceImplTest {

    @Mock
    private OpenWeather openWeather;

    @InjectMocks
    private ForecastServiceImpl forecastService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRequestWeatherForecast_Success() throws Exception {
        // Arrange
        String cityName = "London";
        WeatherForecast expectedWeatherForecast = WeatherForecast.builder().build();
        expectedWeatherForecast.setCityName(cityName);

        // Mock the behavior of OpenWeather
        when(openWeather.requestCityCoordinates(cityName)).thenReturn(new Double[]{51.5074, -0.1278});
        when(openWeather.requestWeatherForecast(51.5074, -0.1278)).thenReturn(expectedWeatherForecast);

        // Act
        WeatherForecast weatherForecast = forecastService.requestWeatherForecast(cityName);

        // Assert
        assertEquals(expectedWeatherForecast, weatherForecast);
        assertEquals(cityName, weatherForecast.getCityName());
    }

    @Test
    public void testRequestWeatherForecast_Exception() throws Exception {
        // Arrange
        String cityName = "NonexistentCity";

        // Mock the behavior of OpenWeather
        when(openWeather.requestCityCoordinates(cityName)).thenThrow(new Exception("City not found"));

        // Act
        WeatherForecast weatherForecast = forecastService.requestWeatherForecast(cityName);

        // Assert
        assertNull(weatherForecast);
    }
}
