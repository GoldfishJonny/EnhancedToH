package com.team4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Leaderbaord manager for towers of hanoi
 * It retrieves the elapsed time returned by TimeTrial and stores it in leaderboard.txt
 * It orders the times from best to worst and returns it
 *
 * @Author Du Tran
 *
 */
public class LeaderBoardManager {
    private static LeaderBoardManager instance;

    public LeaderBoardManager() {}

    public static LeaderBoardManager getInstance() {
        if (instance == null) {
            instance = new LeaderBoardManager();
        }
        return instance;
    }
    private static final String FILE_NAME = "leaderboard.txt";
    private List<LeaderBoardObserver> observers = new ArrayList<>();

    public interface LeaderBoardObserver {
        void onUpdate();
    }

    public void addObserver(LeaderBoardObserver observer) {
        observers.add(observer);
    }

    void notifyObservers() {
        for (LeaderBoardObserver observer : observers) {
            observer.onUpdate();
        }
    }

    public void addTime(String formattedTime) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(formattedTime);
            notifyObservers(); // Notify observers after updating the leaderboard
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the leaderboard file: " + e.getMessage());
        }
    }

    public List<String> getTopTimes(int numberOfTopTimes) {
        List<String> times = new ArrayList<>();
        // Ensure file is re-read each time
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                times.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the leaderboard file: " + e.getMessage());
        }

        Collections.sort(times);
        return times.size() <= numberOfTopTimes ? times : times.subList(0, numberOfTopTimes);
    }

}