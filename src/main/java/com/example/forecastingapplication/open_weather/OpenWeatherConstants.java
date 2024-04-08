package com.example.forecastingapplication.open_weather;

public class OpenWeatherConstants {
    public static final String COORDINATES_URL = "http://api.openweathermap.org/geo/1.0/direct";
    public static final String WEATHER_FORECAST_URL = "https://api.openweathermap.org/data/3.0/onecall";
    public static final String CITY_NAME = "q"; // name of the parameter required by OpenWeather API
    public static final String LIMIT = "limit"; // Number of the locations in the API response (up to 5 results can be returned in the API response)
    public static final Integer TIMEOUT = 5000; // ms
    public static final String API_KEY = "appid";
    public static final String ONE = "1";
    public static final String GET = "GET";
    public static final String QUESTION_MARK = "?";
    public static final String EQUALS = "=";
    public static final String AND = "&";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";
    public static final String UNITS = "units";
    public static final String METRIC = "metric";
    public static final String DAILY = "daily";
    public static final String TEMPERATURE = "temp";
    public static final String MAX = "max";
    public static final String FEELS_LIKE = "feels_like";
    public static final String DAY = "day";
    public static final String NIGHT = "night";
    public static final String EVENING = "eve";
    public static final String MORNING = "morn";
    public static final String HUMIDITY = "humidity";
}
