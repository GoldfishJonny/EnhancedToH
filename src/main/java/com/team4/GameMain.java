package com.team4;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Jonathan Jara
 */

 public class GameMain extends JFrame {
    private MenuPanel menuPanel = new MenuPanel();
    private NavbarPanel navbarPanel;
    private TowerPanel towerPanel;
    private TutorPanel tutorPanel;
    private HelpPanel helpPanel;
    private TimeTrialPanel timeTrial = new TimeTrialPanel(new TimeTrial());
    private ProgressPanel progressPanel;
    private JPanel panel1;
    private LeaderBoardPanel leaderboardPanel = new LeaderBoardPanel(new LeaderBoardManager());


    public GameMain(){
        this.setBackground(Color.WHITE);
        navbarPanel = new NavbarPanel();
        towerPanel = new TowerPanel();
        tutorPanel = new TutorPanel();
        progressPanel = new ProgressPanel();

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.CENTER);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);
        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                File f = new File("data.json");
                if (f.exists() && !f.isDirectory()) {
                    f.delete();
                }
                JSONObject data = new JSONObject();
                GameData.getInstance().getProcessData().saveData(data);
            }
        });
    }
        public static void main(String[] args) {
            GameMain main = new GameMain();
            main.setTitle("Towers of Hanoi");
            main.setSize(800, 400);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
        }


    public void startGame() {
        remove(menuPanel); // Removing the main menu panel

        // Preparing panels
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        // Setting backgrounds and adding components
        towerPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.setBackground(Color.decode("#EFF7F6"));
        leftPanel.add(towerPanel);
        leftPanel.add(timeTrial);

        rightPanel.add(tutorPanel, BorderLayout.CENTER);
        rightPanel.add(leaderboardPanel, BorderLayout.SOUTH);  // Add leaderboard to the bottom of the right panel

        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);

        // Add panels to the main frame
        add(navbarPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        // Setup interactions
        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);

        // Load game data if available
        File f = new File("data.json");
        if (f.exists() && !f.isDirectory()) {
            GameData.getInstance().getProcessData().loadData();
        }

        // Add progress panel
        add(progressPanel, BorderLayout.NORTH);

        // Refresh the UI
        revalidate();
        repaint();
    }


    public void other(){
        System.out.println("Other gamemode");
    }

    public void help(){
        getContentPane().removeAll(); // Remove all components from the JFrame

        HelpPanel helpPanel = new HelpPanel(); // Assuming HelpPanel is already implemented
        add(helpPanel, BorderLayout.CENTER); // Add HelpPanel to the center of the JFrame

        validate(); // Validate the container after components added
        repaint(); // Repaint the container to reflect the changes
    }
 }