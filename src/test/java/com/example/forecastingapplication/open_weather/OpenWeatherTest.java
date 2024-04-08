package com.example.forecastingapplication.open_weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.HttpURLConnection;
import java.net.URL;

class OpenWeatherTest {
    @Mock
    private URL mockUrl;

    @Mock
    private HttpURLConnection mockConnection;

    @InjectMocks
    private OpenWeather openWeather;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRequestWeatherForecast_Success() throws Exception {

    }

    @Test
    public void testRequestWeatherForecast_EmptyResponse() throws Exception {

    }
}