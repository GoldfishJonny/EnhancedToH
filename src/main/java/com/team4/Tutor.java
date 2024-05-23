package com.team4;

import java.awt.*;
import java.util.List;

public class Tutor {
    private String move = "Click the 'Help' button to see the solution";

    private int disk;
    private int tower;
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(move, 10, 10);
    }

    public void setMove(int[] current, int[] next) {
        // only one value is changed in one of the n indexes
        int n = current.length;
        for (int i = 0; i < n; i++) {
            if (current[i] != next[i]) {
                disk= i;
                tower = next[i];
                move = "Move disk " + (i+1) + " from tower " + (current[i]+1) + " to tower " + (next[i]+1);
                break;
            }
        }
    }
    public NewDisk getDisk() {
        NewDisk dis = GameData.getInstance().getNewDisks().get(GameData.getInstance().getNDisks()-1-disk);
        return dis;
    }

    public Tower getTower() {
        return GameData.getInstance().getTowers().get(tower);
    }
}
