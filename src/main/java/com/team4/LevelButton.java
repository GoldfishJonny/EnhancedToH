package com.team4;

import com.team4.GameData;
import com.team4.Scene;

import javax.swing.*;
import java.awt.*;

public class LevelButton extends JButton {
    public LevelButton(ImageIcon icon, int nDisks) {
        super(icon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        addActionListener(e -> {
            GameData.getInstance().setNDisks(nDisks);
            if (GameData.getInstance().getMode() instanceof TimerMode) {
                System.out.println("Timer Mode");
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add("Select Time");
                setComponentPopupMenu(new JPopupMenu());
                popupMenu.show(this, 200, 200);
            } else {
                GameData.getInstance().reloadScene(Scene.GAME);
            }
        });
    }
}
