package com.team4;

import javax.swing.*;
import java.awt.*;

/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Jonathan Jara
 */

 public class GameMain extends JFrame {
     public GameMain(){
         TowerPanel towerPanel = new TowerPanel();
         ChatPanel chatPanel = new ChatPanel();

         this.setLayout(new GridLayout(1,2));
         this.add(towerPanel);
         this.add(chatPanel);

         GameController controller = new GameController();
         towerPanel.addMouseListener(controller);
         towerPanel.addMouseMotionListener(controller);
         towerPanel.addComponentListener(controller);

         GameData.getInstance().setnDisks(3);
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