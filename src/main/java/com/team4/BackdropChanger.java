package com.team4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// work in progress
public class BackdropChanger {

    // api endpts and key
    private static final String API_KEY = "gpxQSsgEAa9nPQo3R8qmWOo3EteBLypi";
    private static final String API_ENDPOINT = "http://dataservice.accuweather.com/currentconditions/v1/";

    public static void changeBackdrop() {
        try {
            // make a request to AccuWeather's API for San Luis Obispo (location key: 331999)
            URL url = new URL(API_ENDPOINT + "331999?apikey=" + API_KEY);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // read the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // parse json response for weather data
            String weatherData = response.toString();

            // extract
            String weatherCondition = parseWeatherCondition(weatherData);

            String backdropImage = determineBackdropImage(weatherCondition);

//            updateGameBackdrop(backdropImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String parseWeatherCondition(String weatherData) {
        try {
            JSONObject jsonObject = new JSONObject(weatherData);
            JSONArray weatherArray = jsonObject.getJSONArray("WeatherText");
            if (weatherArray.length() > 0) {
                return weatherArray.getString(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String determineBackdropImage(String weatherCondition) {
        switch (weatherCondition.toLowerCase()) {
            case "rain":
                return "rainy.jpg";
            case "cloudy":
                return "cloudy.jpg";
            case "sunny":
                return "sunny.jpg";
            default:
                return "default.jpg";
        }
    }

//    private static void updateGameBackdrop(String backdropImage) {
//        GameData.getInstance().setBackdropImage(backdropImage);
//    }

}
