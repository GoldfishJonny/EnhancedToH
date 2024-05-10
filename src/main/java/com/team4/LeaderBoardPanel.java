package com.team4;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Leaderbaord panel for towers of hanoi
 * updates and displays the score on the game panel
 *
 * @Author Du Tran
 *
 */
public class LeaderBoardPanel extends JPanel implements LeaderBoardManager.LeaderBoardObserver {
    private final LeaderBoardManager leaderBoardManager;

    public LeaderBoardPanel(LeaderBoardManager manager) {
        this.leaderBoardManager = manager;
        this.leaderBoardManager.addObserver(this); // Register as observer
        setBorder(BorderFactory.createTitledBorder("Top 5 Scores"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updateScores();
    }

    public void updateScores() {
        removeAll(); // Remove all previous score labels
        List<String> topScores = leaderBoardManager.getTopTimes(5);
        for (String score : topScores) {
            add(new JLabel(score));
        }
        validate();
        repaint();
    }

    @Override
    public void onUpdate() {
        updateScores();
    }
}


