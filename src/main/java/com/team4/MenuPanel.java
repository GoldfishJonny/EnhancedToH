package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton startButton;
    private JButton otherButton;
    private JButton helpButton;

    public MenuPanel() {
        setLayout(new BorderLayout());

        // Set background color of the menu panel
        setBackground(Color.BLACK);

        // Adding the background image
        ImageIcon backgroundImage = new ImageIcon("wipH.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight())); // Set preferred size
        add(backgroundLabel, BorderLayout.CENTER);
        //THIS DOESNT WORK BTW- Du lmao

        // Panel for the buttons
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(); // Create a panel for buttons
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        buttonPanel.add(Box.createVerticalStrut(10));
        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        startButton.setBackground(Color.RED);
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

        return buttonPanel;
    }
}

