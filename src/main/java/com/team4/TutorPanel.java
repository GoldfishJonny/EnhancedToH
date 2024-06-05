package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TutorPanel  extends JPanel implements PropertyChangeListener {
    private JButton helpButton;
    private JButton treeButton;

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

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

        treeButton = new JButton("Generate Tree");
        treeButton.setBackground(Color.decode("#F7D6E0"));
        treeButton.addActionListener(e -> {
            runPythonScript();
            repaint();
        });

        buttonPanel.add(treeButton);
        buttonPanel.add(helpButton);
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
    }


    private void runPythonScript() {
        ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main/resources/help.py");
        try {
            Process process = processBuilder.start();
            // Optionally handle the script's output here
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor(); // Wait for the process to finish if necessary
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to run help script", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
