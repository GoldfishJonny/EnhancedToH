package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TowerPanel extends JPanel implements PropertyChangeListener {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        for (Tower tower : GameData.getInstance().getNewTowers()) {
            tower.draw(g);
        }
        for (NewDisk disk : GameData.getInstance().getNewDisks()) {
            disk.draw(g);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
