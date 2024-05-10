package com.team4;

import javax.swing.*;
import java.awt.*;
/*
Plan is to have a help panel that shows what the game looks like and holds your hand throughout one play through of the game
while also explaining the rules of the game. Demonstrations of what you can and cannot do and explanations of everything on
the screen.
 */

public class HelpPanel extends JPanel {
    public HelpPanel() {
        // Set the layout for the whole panel
        setLayout(new GridLayout(1, 2));  // One row, two columns

        // Create the panel for images
        JPanel imagePanel = new JPanel(new GridLayout(3, 1));  // Three rows, one column
        imagePanel.setBorder(BorderFactory.createTitledBorder("Examples"));
        // Load and add images
        ImageIcon moveAllowed = new ImageIcon(getClass().getResource("/move_allowed.png"));
        //ImageIcon moveNotAllowed = new ImageIcon(getClass().getResource("/move_not_allowed.png"));
        //ImageIcon exampleGame = new ImageIcon(getClass().getResource("/example_game.png"));

        imagePanel.add(new JLabel(moveAllowed));
        //imagePanel.add(new JLabel(moveNotAllowed));
        //imagePanel.add(new JLabel(exampleGame));

        // Create the panel for text
        JTextArea textArea = new JTextArea("Rules of Towers of Hanoi:\n\n" +
                "- You can only move one disk at a time.\n" +
                "- Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.\n" +
                "- You cannot place a larger disk onto a smaller disk.\n" +
                "- The objective is to move all disks from the first peg to the last peg, maintaining the order.");
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane textScrollPane = new JScrollPane(textArea);

        // Add the sub-panels to the main panel
        add(imagePanel);
        add(textScrollPane);
    }
}
