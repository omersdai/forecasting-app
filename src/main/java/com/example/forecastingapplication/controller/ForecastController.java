package com.example.forecastingapplication.controller;

import com.example.forecastingapplication.service.ForecastService;
import com.example.forecastingapplication.model.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ForecastController {
    private final ForecastService service;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.service = forecastService;
    }

    /**
     * Get weather forecast service.
     *
     * @return the Weather Forecast with status 200 (OK)
     */
    @GetMapping("/forecast")
    public ResponseEntity<WeatherForecast> getWeatherForecast(@RequestParam(name = "city") String city){
        WeatherForecast weatherForecast = service.requestWeatherForecast(city);
        HttpStatus httpStatus = weatherForecast != null ? HttpStatus.OK : HttpStatus.BAD_GATEWAY;

        return new ResponseEntity<>(weatherForecast, httpStatus);
    }
}
