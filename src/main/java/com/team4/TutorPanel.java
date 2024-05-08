package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class TutorPanel  extends JPanel implements PropertyChangeListener {
    private JButton helpButton;

    public TutorPanel() {
        initializeComponents();
    }
    Tutor tutor = GameData.getInstance().getTutor();
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tutor.draw(g);
    }

    public void initializeComponents() {
        setBackground(Color.decode("#EFF7F6"));

        helpButton = new JButton("Help");
        helpButton.setBackground(Color.decode("#F7D6E0"));
        helpButton.addActionListener(e -> {
            // Call the method to restart the game from GameData
            int[] m = GameData.getInstance().getDisks();
            List<int[]> n = GameData.getInstance().solveGame();
            tutor.setMove(m, n.get(0));
            GameData.getInstance().setAskedForHelp(tutor.getDisk(), tutor.getTower());
            // Repaint the panel to reflect the changes
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
