package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuScene extends JPanel {
    private JButton startButton;
    private JButton otherButton;
    private JButton helpButton;

    public MenuScene() {
        setLayout(new BorderLayout());

        // Set background color of the menu panel
        setBackground(Color.BLACK);

        // Adding the background image
//        ImageIcon backgroundImage = new ImageIcon("./src/main/resources/t3.png");
        ImageIcon backgroundImage = new ImageIcon(GameMain.class.getResource("/t3.png"));
        repaint();
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
        startButton = new JButton("Timer");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 15));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameData.getInstance().reloadScene(Scene.TIMER);
            }
        });
        buttonPanel.add(startButton); // Add the start button to the buttonPanel
        // Add spacing to position the buttons vertically
        buttonPanel.add(Box.createVerticalStrut(20));

        otherButton = new JButton("Stop Watch");
        otherButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        otherButton.setBackground(Color.BLUE);
        otherButton.setForeground(Color.WHITE); // Set the text color to white
        otherButton.setFont(new Font("Arial", Font.BOLD, 15));

        otherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).other();
            }
        });
        buttonPanel.add(otherButton); // Add the other button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));

        helpButton = new JButton("Help");
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        helpButton.setBackground(Color.GREEN);
        helpButton.setForeground(Color.WHITE);
        helpButton.setFont(new Font("Arial", Font.BOLD, 15));
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).help();
            }
        });
        buttonPanel.add(helpButton); // Add the help button to the buttonPanel
        buttonPanel.add(Box.createVerticalStrut(20));

        return buttonPanel;
    }
}