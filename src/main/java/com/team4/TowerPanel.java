package com.team4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TowerPanel extends JPanel implements PropertyChangeListener {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.decode("#EFF7F6"));
        for (Tower tower : GameData.getInstance().getNewTowers()) {
            tower.draw(g);
        }
        for (NewDisk disk : GameData.getInstance().getNewDisks()) {
            disk.draw(g);
        }
    }
        private JButton restartButton;

        public TowerPanel() {
            setBackground(Color.decode("#EFF7F6"));
            initializeComponents();
        }

        private void initializeComponents() {
            setBackground(Color.WHITE);

            restartButton = new JButton("Restart Game");
            restartButton.setBackground(Color.decode("#F7D6E0"));
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call the method to restart the game from GameData
                    GameData.getInstance().restart();
                    // Repaint the panel to reflect the changes
                    repaint();
                }
            });

            this.add(restartButton, BorderLayout.NORTH);
        }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
