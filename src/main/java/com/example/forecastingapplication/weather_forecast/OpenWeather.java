package com.example.forecastingapplication.weather_forecast;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeather {
    private static final String COORDINATES_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String CITY_NAME = "q"; // name of the parameter required by OpenWeather API
    private static final String LIMIT = "limit"; // Number of the locations in the API response (up to 5 results can be returned in the API response)
    private static final Integer TIMEOUT = 5000; // ms
    private static final String API_KEY = "appid";
    private static final String ONE = "1";
    private static final String GET = "GET";
    private static final String UTF_8 = "UTF-8";
    private static final String QUESTION_MARK = "?";
    private static final String EQUALS = "=";
    private static final String AND = "&";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";

    private final String apiKey;


    public OpenWeather(@Value("${api-key}") String apiKey){
        this.apiKey = apiKey;
    }

    private Double[] getCityCoordinates(String cityName) throws Exception {

        Map<String, String> parameters = buildParams(cityName, apiKey);

        String url = buildUrl(COORDINATES_URL, parameters);
        String jsonResponse = sendRequest(url);
        Double[] coordinates = parseCoordinates(jsonResponse);

        return coordinates;
    }

    private static String buildUrl(String URL, Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(URL);
        sb.append(QUESTION_MARK);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(URLEncoder.encode(entry.getKey(), UTF_8));
            sb.append(EQUALS);
            sb.append(URLEncoder.encode(entry.getValue(), UTF_8));
            sb.append(AND);
        }

        return sb.toString();
    }

    private static String sendRequest(String urlString) throws Exception {
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

        if(jsonArray == null || jsonArray.size() == 0) throw new Exception("City Array does not exist!");

        JsonObject city = jsonArray.get(0).getAsJsonObject();

        Double latitude = city.get(LATITUDE).getAsDouble();
        Double longitude = city.get(LONGITUDE).getAsDouble();

        return new Double[]{latitude, longitude};
    }

    private static Map<String, String> buildParams(String cityName, String apiKey){
        Map<String, String> parameters = new HashMap<>();
        parameters.put(CITY_NAME, cityName);
        parameters.put(API_KEY, apiKey);
        parameters.put(LIMIT, ONE);

        return parameters;
    }
}
