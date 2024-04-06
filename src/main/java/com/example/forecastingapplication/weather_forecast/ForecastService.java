package com.example.forecastingapplication.weather_forecast;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class ForecastService {
    private final OpenWeather openWeather;

    public ForecastService(OpenWeather openWeather){
        this.openWeather = openWeather;
    }


}
