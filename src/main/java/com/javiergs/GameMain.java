package com.javiergs;
import javax.swing.*;
import java.awt.*;


/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Professor
 */
public class GameMain extends JFrame {

  public GameMain() {

    TowerPanel towerPanel = new TowerPanel();
    ChatPanel chatPanel = new ChatPanel();
    WeatherPanel weatherPanel = new WeatherPanel();
    MapPanel mapPanel = new MapPanel();
    JPanel rightPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    rightPanel.setLayout (new GridLayout(2,1));
    rightPanel.add(towerPanel);
    rightPanel.add(bottomPanel);

    bottomPanel.setLayout (new GridLayout(1,2));
    bottomPanel.add(weatherPanel);
    bottomPanel.add(mapPanel);
    this.setLayout (new GridLayout(1,2));
    add(chatPanel);
    add(rightPanel);

    GameController controller = new GameController();
    towerPanel.addMouseListener(controller);
    towerPanel.addMouseMotionListener(controller);
    towerPanel.addComponentListener(controller);

    GameData.getInstance().setnDisks(4);
    GameData.getInstance().setSize(this.getWidth(), this.getHeight());
    GameData.getInstance().addPropertyChangeListener(towerPanel);
  }

  public static void main(String[] args) {

    GameMain main = new GameMain();
    main.setTitle("Towers of Hanoi");
    main.setSize(800, 400);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }
}
