package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MenuScene extends JPanel {

    public MenuScene() {
        setLayout(new BorderLayout());

        // Set background color of the menu panel
        setBackground(Color.BLACK);
        
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(GameMain.class.getResource("/t3.png")));
        repaint();
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight())); // Set preferred size
        add(backgroundLabel, BorderLayout.CENTER);
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(); // Create a panel for buttons
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        buttonPanel.add(Box.createVerticalStrut(10));
        JButton startButton = new Button("Timer", Color.RED, Color.WHITE, Scene.TIMER);
        buttonPanel.add(startButton); // Add the start button to the buttonPanel
        // Add spacing to position the buttons vertically
        buttonPanel.add(Box.createVerticalStrut(20));

        JButton otherButton = new Button("Stopwatch", Color.BLUE, Color.WHITE, Scene.STOPWATCH);
        buttonPanel.add(otherButton); // Add the other button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));

        JButton helpButton = new Button("Help", Color.GREEN, Color.WHITE, Scene.HELP);
        buttonPanel.add(helpButton); // Add the help button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));
        return buttonPanel;
    }
}