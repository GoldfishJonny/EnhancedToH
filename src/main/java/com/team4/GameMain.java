package com.team4;

import javax.swing.*;
import java.awt.*;

public class GameMain extends JFrame {
    private MenuPanel menuPanel;
    private TowerPanel towerPanel;
    private ChatPanel chatPanel;


    public GameMain() {
        setTitle("Towers of Hanoi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        // Initialize components
        menuPanel = new MenuPanel();
        towerPanel = new TowerPanel();
        chatPanel = new ChatPanel();

        // Set layout
        setLayout(new BorderLayout());

        // Add main menu panel initially
        add(menuPanel, BorderLayout.CENTER);

    /*
    // Add tower panel and other components
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new GridLayout(2, 1));
    rightPanel.add(towerPanel);
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout(1, 2));
    bottomPanel.add(weatherPanel);
    bottomPanel.add(mapPanel);
    rightPanel.add(bottomPanel);
    add(chatPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
     */

        // Set up game controller and data
        GameController controller = new GameController();
        towerPanel.addMouseListener(controller);
        towerPanel.addMouseMotionListener(controller);
        towerPanel.addComponentListener(controller);
        GameData.getInstance().setnDisks(4);
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().addPropertyChangeListener(towerPanel);
    }

    public void startGame() {
        // Remove main menu panel
        remove(menuPanel);

        // Add other panels
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(towerPanel);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        rightPanel.add(bottomPanel);

        // Add chat panel to the left
        add(chatPanel, BorderLayout.WEST);

        // Add right panel to the center
        add(rightPanel, BorderLayout.CENTER);

        // Revalidate and repaint
        revalidate();
        repaint();
    }
        public static void main(String[] args) {
            GameMain main = new GameMain();
            main.setTitle("Towers of Hanoi");
            main.setSize(800, 400);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
        }
 }