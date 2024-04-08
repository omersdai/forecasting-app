package com.example.forecastingapplication.service;

import com.example.forecastingapplication.model.WeatherForecast;

public interface ForecastService {
    WeatherForecast requestWeatherForecast(String cityName);
}
