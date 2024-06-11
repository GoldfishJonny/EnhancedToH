package com.team4;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HelpPanel extends JPanel {
    public HelpPanel() {
        // Set the layout for the whole panel
        setLayout(new GridLayout(1, 2));

        // Create the panel for images
        JPanel imagePanel = new JPanel(new GridLayout(3, 1));
        imagePanel.setBorder(BorderFactory.createTitledBorder("Examples"));
        // Load and add images
        ImageIcon moveAllowed = new ImageIcon(Objects.requireNonNull(getClass().getResource("/move_allowed.png")));
        ImageIcon moveNotAllowed = new ImageIcon(Objects.requireNonNull(getClass().getResource("/move_not_allowed.png")));
        imagePanel.add(new JLabel(resizeImageIcon(moveAllowed)));
        imagePanel.add(new JLabel(resizeImageIcon(moveNotAllowed)));

        // Add the image panel to the main panel
        add(imagePanel);
        JPanel textPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(" Rules of Towers of Hanoi:- You can only move one disk at a time." + '\n' +
                 "Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack." + '\n' +
                 "You cannot place a larger disk onto a smaller disk." + '\n' +
                 "The objective is to move all disks from the first peg to the last peg, maintaining the order.");
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.BOLD, 16));

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textPanel.add(textScrollPane, BorderLayout.CENTER);

        // Create and add the button
        JButton backButton = new Button("Back To Menu", Color.BLACK, Color.WHITE, Scene.MENU);
        textPanel.add(backButton, BorderLayout.SOUTH); // Add button at the bottom

        // Add the text panel to the main panel
        add(textPanel);
    }

    private ImageIcon resizeImageIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}

