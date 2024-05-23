package com.team4;

import com.team4.GameData;
import com.team4.Scene;

import javax.swing.*;
import java.awt.*;

public class TimeButton extends JButton {
    public TimeButton(String text, int time) {
        super(text);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        addActionListener(e -> {
            GameData.getInstance().setSelectedTime(time);
            GameData.getInstance().reloadScene(Scene.GAME);
        });
    }
}
