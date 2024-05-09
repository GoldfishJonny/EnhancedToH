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
                JSONObject data = new JSONObject();
                File f = new File("data.json");
                if (f.exists() && !f.isDirectory()) {
                    f.delete();
                }
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
        // Remove main menu panel
        remove(menuPanel);

        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
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
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
        this.add(navbarPanel);
        this.add(bottomPanel);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);

        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(this.getWidth(), this.getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);
        //if file data.json exists, load data from file
        File f = new File("data.json");
        if (f.exists() && !f.isDirectory()) {
            GameData.getInstance().getProcessData().loadData();
        }

        ProgressPanel progressPanel = GameData.getInstance().getProgressPanel();
        this.add(progressPanel, BorderLayout.NORTH);
        System.out.println("ProgressPanel added to GameMain");

        // Revalidate and repaint
        revalidate();
        repaint();
    }

    public void other(){
        System.out.println("Other gamemode");
    }

    public void help(){
        // Remove main menu panel
        remove(menuPanel);

        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
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
        bottomPanel.add(leftPanel);
        bottomPanel.add(rightPanel);
        this.add(navbarPanel);
        this.add(bottomPanel);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);

        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(this.getWidth(), this.getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);

        ProgressPanel progressPanel = GameData.getInstance().getProgressPanel();
        this.add(progressPanel, BorderLayout.NORTH);
        System.out.println("ProgressPanel added to GameMain");

        // Revalidate and repaint
        revalidate();
        repaint();
    }
 }