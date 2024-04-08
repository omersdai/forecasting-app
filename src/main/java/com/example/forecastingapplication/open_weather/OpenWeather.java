package com.example.forecastingapplication.open_weather;

import com.example.forecastingapplication.model.WeatherForecast;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.example.forecastingapplication.open_weather.OpenWeatherConstants.*;

@Service
public class OpenWeather {
    private final String apiKey;

    public OpenWeather(@Value("${api-key}") String apiKey){
        this.apiKey = apiKey;
    }

    public WeatherForecast requestWeatherForecast(Double latitude, Double longitude) throws Exception{
        Map<String, String> parameters = new HashMap<>();
        parameters.put(LATITUDE, latitude + "");
        parameters.put(LONGITUDE, longitude + "");
        parameters.put(API_KEY, apiKey);
        parameters.put(UNITS, METRIC);

        String url = buildUrl(WEATHER_FORECAST_URL, parameters);
        String jsonResponse = sendGetRequest(url);

        return parseWeatherForecast(jsonResponse);
    }

    public Double[] requestCityCoordinates(String cityName) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(CITY_NAME, cityName);
        parameters.put(API_KEY, apiKey);
        parameters.put(LIMIT, ONE);

        String url = buildUrl(COORDINATES_URL, parameters);
        String jsonResponse = sendGetRequest(url);

        return parseCoordinates(jsonResponse);
    }

    private WeatherForecast parseWeatherForecast(String jsonResponse) throws Exception{
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        Double latitude = jsonObject.get(LATITUDE).getAsDouble();
        Double longitude = jsonObject.get(LONGITUDE).getAsDouble();

        JsonArray jsonArray = jsonObject.get(DAILY).getAsJsonArray();

        if(jsonArray == null || jsonArray.isEmpty()) throw new Exception("Daily Forecast Array does not exist!");

        JsonObject dailyForecast = jsonArray.get(0).getAsJsonObject();

        JsonObject temperature = dailyForecast.get(TEMPERATURE).getAsJsonObject();
        Double maxTemperature = temperature.get(MAX).getAsDouble();

        JsonObject feelsLike = dailyForecast.getAsJsonObject(FEELS_LIKE).getAsJsonObject();
        Double morningTemperature = feelsLike.get(MORNING).getAsDouble();
        Double dayTemperature = feelsLike.get(DAY).getAsDouble();
        Double eveningTemperature = feelsLike.get(EVENING).getAsDouble();
        Double nightTemperature = feelsLike.get(NIGHT).getAsDouble();

        Double humidity = dailyForecast.get(HUMIDITY).getAsDouble();


        return WeatherForecast.builder()
                .latitude(latitude)
                .longitude(longitude)
                .maxTemperature(maxTemperature)
                .morningTemperature(morningTemperature)
                .dayTemperature(dayTemperature)
                .eveningTemperature(eveningTemperature)
                .nightTemperature(nightTemperature)
                .humidity(humidity)
                .build();
    }

    private static String buildUrl(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);
        sb.append(QUESTION_MARK);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            sb.append(EQUALS);
            sb.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            sb.append(AND);
        }

        return sb.toString();
    }

    private static String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(GET);
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);

        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        return result.toString();
    }

    private static Double[] parseCoordinates(String jsonResponse) throws Exception{

        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);

        if(jsonArray == null || jsonArray.isEmpty()) throw new Exception("City Array does not exist!");

        JsonObject city = jsonArray.get(0).getAsJsonObject();

        double latitude = city.get(LATITUDE).getAsDouble();
        double longitude = city.get(LONGITUDE).getAsDouble();

        return new Double[]{latitude, longitude};
    }

}
