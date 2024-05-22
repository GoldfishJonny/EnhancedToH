package com.team4;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String text, Font font, Color foregroundColor) {
        super(text);
        setFont(font);
        setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        setForeground(foregroundColor);
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
