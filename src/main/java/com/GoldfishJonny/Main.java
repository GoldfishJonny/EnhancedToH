package com.GoldfishJonny;

import com.team4.LevelScene;

import javax.swing.*;

public class Main extends JFrame {

    public Main(){
        LevelScene levelScene = new LevelScene();
        add(levelScene);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setTitle("Test");
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
