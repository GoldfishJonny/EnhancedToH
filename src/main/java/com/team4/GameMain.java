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
         TutorPanel tutorPanel = new TutorPanel();
         TimeTrialPanel timeTrial = new TimeTrialPanel(new TimeTrial());
         JPanel rightPanel = new JPanel();
         JPanel leftPanel = new JPanel();

         this.setLayout(new GridLayout(1,2));
         rightPanel.setLayout(new GridLayout(2,1));
         leftPanel.setLayout(new GridLayout(2,1));
         leftPanel.add(towerPanel);
         leftPanel.add(timeTrial);
         rightPanel.add(tutorPanel);
         this.add(leftPanel);
         this.add(rightPanel);

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