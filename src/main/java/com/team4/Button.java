package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(String text, Color Backgroundcolor, Color Foregroundcolor, Scene scene) {
        super(text);
        setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        setAlignmentX(CENTER_ALIGNMENT);
        setBackground(Backgroundcolor);
        setForeground(Foregroundcolor);
        addActionListener(e -> GameData.getInstance().reloadScene(scene));
    }
}
