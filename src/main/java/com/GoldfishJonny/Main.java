package com.GoldfishJonny;

import com.team4.GameData;
import com.team4.Scene;
//import com.team4.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        this.setBackground(Color.WHITE);
        GameData.getInstance().setFrame(this);
        JPanel scene = GameData.getInstance().switchScene(Scene.MENU);
        setLayout(new BorderLayout());
        add(scene, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.setTitle("tests scenes");
        main.setSize(800,400);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JMenuBar bar = new JMenuBar();
//        JMenu file = new JMenu("Settings");
//        JMenuItem lmao = new JMenuItem("Restart");
//        file.add(lmao);
//        bar.add(file);
//        main.setJMenuBar(bar);
//        main.setLocationByPlatform(true);
        main.setVisible(true);
    }
}
