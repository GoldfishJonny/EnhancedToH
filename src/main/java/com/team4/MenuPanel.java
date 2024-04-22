package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private JButton startButton;

    public MenuPanel() {
        setLayout(new BorderLayout()); // Set layout to BorderLayout

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the startGame method in GameMain when the start button is clicked
                ((GameMain)SwingUtilities.getWindowAncestor(MenuPanel.this)).startGame();
            }
        });

        add(startButton, BorderLayout.CENTER); // Add the start button to the center
    }

}