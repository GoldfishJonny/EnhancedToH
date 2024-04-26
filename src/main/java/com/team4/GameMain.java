package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     private TimeTrialPanel timeTrial = new TimeTrialPanel(new TimeTrial());
    public GameMain(){
        navbarPanel = new NavbarPanel();
        towerPanel = new TowerPanel();
        tutorPanel = new TutorPanel();

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.CENTER);

        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);
        GameData.getInstance().setnDisks(3);
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);
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
        leftPanel.setLayout(new GridLayout(2,1));

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

        // Revalidate and repaint
        revalidate();
        repaint();
    }

    public void other(){
        System.out.println("Other gamemode");
    }

    public void help(){
        System.out.println("HELPPPP");
    }
 }