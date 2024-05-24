package com.team4;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Jonathan Jara
 */

 public class GameMain extends JFrame {
    public GameMain() {
        this.setBackground(Color.WHITE);
        GameData.getInstance().setFrame(this);
        JPanel scene = GameData.getInstance().switchScene(Scene.MENU);
        setLayout(new BorderLayout());
        add(scene, BorderLayout.CENTER);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                File f = new File("Users"+ File.separator + "data.json");
                if (f.exists() && !f.isDirectory()) {
                    if (f.delete()) {
                        System.out.println("File deleted successfully");
                    } else {
                        System.out.println("Failed to delete the file");
                    }
                }
                JSONObject data = new JSONObject();
                GameData.getInstance().getProcessData().saveData(data);
            }
        });
        //QuickStart qs = new QuickStart();
        //qs.loadDataFromFile("Users/data.json");
    }

    public static void main(String[] args) {
        GameMain main = new GameMain();
        main.setTitle("Towers of Hanoi");
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        File f = new File("Users");
        if (!f.exists() || !f.isDirectory()) {
            if (!f.mkdir()){
                System.out.println("Failed to create Users directory");
            }
        }
    }
}