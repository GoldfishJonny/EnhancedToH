package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton startButton;
    private JButton otherButton;
    private JButton helpButton;

    public MenuPanel() {
        setLayout(new BorderLayout()); // Set layout to BorderLayout

        JPanel buttonPanel = new JPanel(); // Create a panel for buttons
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        buttonPanel.add(Box.createVerticalStrut(100));
        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).startGame();
            }
        });
        buttonPanel.add(startButton); // Add the start button to the buttonPanel
        // Add spacing to position the buttons vertically
        buttonPanel.add(Box.createVerticalStrut(20));

        otherButton = new JButton("Other Button");
        otherButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        otherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).other();
            }
        });
        buttonPanel.add(otherButton); // Add the other button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));

        helpButton = new JButton("Help");
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).help();
            }
        });
        buttonPanel.add(helpButton); // Add the help button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));

        add(buttonPanel, BorderLayout.CENTER); // Add the buttonPanel to the center

        // Panel for the image

    }
}
