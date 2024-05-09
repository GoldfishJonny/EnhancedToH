package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/*
Plan is to have a help panel that shows what the game looks like and holds your hand throughout one play through of the game
while also explaining the rules of the game. Demonstrations of what you can and cannot do and explanations of everything on
the screen.
 */
public class HelpPanel  extends JPanel implements PropertyChangeListener {
    private JButton helpButton;

    public HelpPanel() {
        initializeComponents();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void initializeComponents() {
        setBackground(Color.decode("#EFF7F6"));

        helpButton = new JButton("Help");
        helpButton.setBackground(Color.decode("#F7D6E0"));
        helpButton.addActionListener(e -> {
            repaint();
        });
        setLayout(new BorderLayout());
        add(helpButton, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
