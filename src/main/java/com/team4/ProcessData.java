package com.team4;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ProcessData {

    public void saveData(JSONObject data) {
        // Save data to database
        data.put("Username", GameData.getInstance().getUsername());
        data.put("Top Scores Timer", GameData.getInstance().getTopScoresTimer());
        data.put("Top Scores Stopwatch", GameData.getInstance().getTopScoresStopwatch());
        System.out.println("Data saved to database");
        try (FileWriter file = new FileWriter("data.json")) {
            file.write(data.toString());
            file.flush();
        } catch (Exception e) {
            System.out.println("Error saving data to database");
            e.printStackTrace();
        }
    }

    public void loadData() {
        File f = new File("data.json");
        if (!f.exists() || f.isDirectory()) {
            return;
        }
        try (FileReader reader = new FileReader("data.json")) {
            // Read data from database
            JSONTokener jsonParser = new JSONTokener(reader);
            JSONObject data = new JSONObject(jsonParser);
            GameData.getInstance().setUsername(data.getString("Username"));
            GameData.getInstance().setTopScoresTimer(data.getJSONArray("Top Scores Timer"));
            GameData.getInstance().setTopScoresStopwatch(data.getJSONArray("Top Scores Stopwatch"));
            System.out.println("Data loaded from database");
        } catch (Exception e) {
            System.out.println("Error loading data from database");
            e.printStackTrace();
        }
    }
}
