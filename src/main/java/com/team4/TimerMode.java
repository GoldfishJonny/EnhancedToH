package com.team4;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMode extends Mode{
    @Override
    public void start() {
        GameData game = GameData.getInstance();
        boolean running = game.getRunning();
        if (!running) {
            Timer timer = game.getTimer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    game.setElapsedTime(game.getElapsedTime() - 100);
                }
            }, 0, 100);
            game.setRunning(true);
        }
    }
}
