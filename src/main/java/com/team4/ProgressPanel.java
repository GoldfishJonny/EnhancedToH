package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressPanel extends JPanel {
    private int progressPercentage;
    private Timer animationTimer;
    private int animationSpeed = 10; // Adjust animation speed as needed

    public ProgressPanel() {
        progressPercentage = 0;
        animationTimer = new Timer(animationSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update progress and repaint the panel
                if (progressPercentage < 100) {
                    progressPercentage++;
                } else {
                    // If progress reaches 100%, stop the animation
                    animationTimer.stop();
                }
                repaint();
            }
        });
    }

    public void startAnimation() {
        progressPercentage = 0; // Reset progress percentage
        animationTimer.start();
    }

    public void stopAnimation() {
        animationTimer.stop();
    }

    public void updateProgress(int newProgressPercentage) {
        progressPercentage = newProgressPercentage;
        repaint();
    }

    public void resetProgress() {
        progressPercentage = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = (int) (getWidth() * (progressPercentage / 100.0));
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, barWidth, getHeight());
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(200, 20); // Adjust panel size as needed
//    }
}
