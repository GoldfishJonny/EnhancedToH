package com.team4;

import com.team4.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
* TimerScene represents a JPanel that displays the game scene with the timer and the tutor.
* It listens for changes in the game data and updates the display accordingly.
* @author Jonathan Jara
*/
public class TimerScene extends JPanel {

    public TimerScene(){
        TowerPanel towerPanel = new TowerPanel();
        TutorPanel tutorPanel = new TutorPanel();
        ProgressPanel progressPanel = new ProgressPanel();
        TimeTrialPanel timeTrial = new TimeTrialPanel(new TimeTrial());
//        LeaderBoardPanel leaderboardPanel = new LeaderBoardPanel(new LeaderBoardManager());


        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        this.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(1,2));
        rightPanel.setLayout(new GridLayout(2,1));
        rightPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new GridLayout(2,1));

        towerPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.add(towerPanel);
        leftPanel.add(timeTrial);

        rightPanel.add(tutorPanel);
//        rightPanel.add(leaderboardPanel);

        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        add(bottomPanel, BorderLayout.CENTER);

        // Setup interactions
        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);

        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);

        // Load game data if available
        File f = new File("data.json");
        if (f.length() == 0 && !f.isDirectory()) {
            GameData.getInstance().getProcessData().loadData();
        }

        // Refresh the UI
        revalidate();
        repaint();

    }
}