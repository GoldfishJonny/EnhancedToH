package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NavbarPanel extends JPanel implements PropertyChangeListener {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.RED);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
