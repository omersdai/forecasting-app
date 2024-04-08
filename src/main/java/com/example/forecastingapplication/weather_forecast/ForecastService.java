package com.example.forecastingapplication.weather_forecast;


import org.springframework.stereotype.Service;



@Service
public class ForecastService {
    private final OpenWeather openWeather;

    public ForecastService(OpenWeather openWeather){
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
