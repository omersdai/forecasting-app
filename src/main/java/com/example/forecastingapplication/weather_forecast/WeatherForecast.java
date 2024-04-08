package com.example.forecastingapplication.weather_forecast;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WeatherForecast {
    private Double latitude;
    private Double longitude;
    private String cityName;
    private Double maxTemperature;
    private Double morningTemperature;
    private Double dayTemperature;
    private Double eveningTemperature;
    private Double nightTemperature;
    private Double humidity;
}
