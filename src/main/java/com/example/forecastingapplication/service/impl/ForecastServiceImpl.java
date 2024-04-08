package com.example.forecastingapplication.service.impl;


import com.example.forecastingapplication.service.ForecastService;
import com.example.forecastingapplication.open_weather.OpenWeather;
import com.example.forecastingapplication.model.WeatherForecast;
import org.springframework.stereotype.Service;



@Service
public class ForecastServiceImpl implements ForecastService {
    private final OpenWeather openWeather;

    public ForecastServiceImpl(OpenWeather openWeather){
        this.openWeather = openWeather;
    }

    public WeatherForecast requestWeatherForecast(String cityName){
        try {
            Double[] cityCoordinates = openWeather.requestCityCoordinates(cityName);
            Double latitude = cityCoordinates[0], longitude = cityCoordinates[1];

            WeatherForecast weatherForecast = openWeather.requestWeatherForecast(latitude, longitude);
            weatherForecast.setCityName(cityName);

            return weatherForecast;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
