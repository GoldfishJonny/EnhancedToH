package com.team4;

import java.awt.*;

public class Tutor {
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Welcome to Tower of Hanoi", 200, 100);
        g.drawString("The objective of the game is to move all the disks from the leftmost tower to the rightmost tower", 50, 150);
        g.drawString("You can only move one disk at a time", 50, 200);
        g.drawString("You can only place a disk on top of a larger disk", 50, 250);
        g.drawString("Good Luck!", 200, 300);
    }
}
