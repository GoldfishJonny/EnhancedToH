package com.team4;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LevelScene extends JPanel {

    public LevelScene() {
        setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,1));
        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.BLACK);
        levelPanel.setLayout(new GridLayout(1, 3));

        ImageIcon disk3 = new ImageIcon(Objects.requireNonNull(GameMain.class.getResource("/3disk.png")));
        ImageIcon disk4 = new ImageIcon(Objects.requireNonNull(GameMain.class.getResource("/4disk.png")));
        ImageIcon disk5 = new ImageIcon(Objects.requireNonNull(GameMain.class.getResource("/5disk.png")));

        LevelButton disk3Label = new LevelButton(disk3,3);
        LevelButton disk4Label = new LevelButton(disk4, 4);
        LevelButton disk5Label = new LevelButton(disk5, 5);

        levelPanel.add(disk3Label);
        levelPanel.add(disk4Label);
        levelPanel.add(disk5Label);

        add(levelPanel);
        if (GameData.getInstance().getMode() instanceof TimerMode) {
            this.setLayout(new GridLayout(2,1));
            JPanel timePanel = new JPanel();
            timePanel.setBackground(Color.BLACK);
            timePanel.setLayout(new GridLayout(1, 3));

            TimeButton time1 = new TimeButton("5 minutes", 300000);
            TimeButton time2 = new TimeButton("1 minute", 60000);
            TimeButton time3 = new TimeButton("30 seconds", 30000);

            timePanel.add(time1);
            timePanel.add(time2);
            timePanel.add(time3);

            add(timePanel);
        }
    }
}
