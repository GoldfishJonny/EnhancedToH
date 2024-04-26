package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TowerPanel extends JPanel implements PropertyChangeListener {
    private ProgressPanel progressPanel;
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
            setBackground(Color.decode("#EFF7F6"));
            progressPanel = new ProgressPanel();

            restartButton = new JButton("Restart Game");
            restartButton.setBackground(Color.decode("#F7D6E0"));
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call the method to restart the game from GameData
                    GameData.getInstance().restart();
                    if (progressPanel != null) {
                        progressPanel.stopAnimation();
                        progressPanel.updateProgress(0); // Reset progress to 0
                    }
                    // Repaint the panel to reflect the changes
                    repaint();
                }
            });

            this.add(restartButton, BorderLayout.CENTER);
            repaint();
        }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    public void setProgressPanel(ProgressPanel progressPanel) {
        this.progressPanel = progressPanel;
    }
}
