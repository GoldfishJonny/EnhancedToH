package com.team4;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ProcessData {

    public void saveData(JSONObject data) {
        // Save data to database
        data.put("Best Time", GameData.getInstance().getBestTime());
//        data.put("Moves", GameData.getInstance().getMoves());
        data.put("Disks", GameData.getInstance().getDisks());
//        System.out.println(data);
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
//            System.out.println(data);
            GameData.getInstance().setBestTime(data.getInt("Best Time"));
//            GameData.getInstance().setMoves(data.getInt("Moves"));
            GameData.getInstance().setDisks(data.getJSONArray("Disks"));
            System.out.println("Data loaded from database");
        } catch (Exception e) {
            System.out.println("Error loading data from database");
            e.printStackTrace();
        }
    }
}
