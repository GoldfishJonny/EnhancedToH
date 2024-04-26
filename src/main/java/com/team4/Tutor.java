package com.team4;

import java.awt.*;
import java.util.List;

public class Tutor {
    private String move = "Click the 'Help' button to see the solution";
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(move, 10, 10);
    }

    public void setMove(String move) {
        this.move = move;
    }
}
