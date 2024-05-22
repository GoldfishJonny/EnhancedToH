package com.team4;

import java.util.Timer;

public abstract class Mode {
    public abstract void start();
    public long stop(){
        GameData game = GameData.getInstance();
        boolean running = game.getRunning();
        Timer timer = game.getTimer();
        if (running){
            timer.cancel();
            game.setRunning(false);
            long elapsedTime = game.getElapsedTime();
            game.setBestTime(elapsedTime);
        }
        System.out.println("Game Over! Time taken: " + game.getElapsedTime());
        return game.getElapsedTime();
    }
    public void restart(){
        GameData game = GameData.getInstance();
        boolean running = game.getRunning();
        if (!running) {
            Timer timer = new Timer();
            game.setTimer(timer);
            game.resetElapsedTime();
            start();
        }
    }

    public String formatElapsedTime() {
        long elapsedTime = GameData.getInstance().getElapsedTime();
        long milliseconds = elapsedTime % 1000;
        long seconds = (elapsedTime / 1000) % 60; // Extract seconds
        long minutes = (elapsedTime / (1000 * 60)) % 60; // Extract minutes
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds); // Format the time string
    }

    public void setElapsedTime(int i) {
        GameData.getInstance().setElapsedTime(i);
    }
}
