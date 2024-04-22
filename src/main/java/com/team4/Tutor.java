package com.team4;

import java.awt.*;
import javax.swing.*;

public class Tutor{
    private Image tutorImage;
    private String message = ""; // Message to be displayed in the speech bubble

    public Tutor() {
        // Load the image. Du Tran
        ImageIcon ii = new ImageIcon("src/main/resources/tutor1.png");
        tutorImage = ii.getImage();
        // End of Du
        //System.out.println("Hello From Tutor!");
    }

    public void draw(Graphics g) {
        //System.out.println("Drawing Tutor!");
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 200, 400, 100, 20, 20); // Bubble rectangle with rounded corners

        // Draw the "tail" of the bubble
        int[] tailX = {150, 140, 150}; // X coordinates for the tail
        int[] tailY = {250, 260, 270}; // Y coordinates for the tail
        g.fillPolygon(tailX, tailY, 3); // Triangle for the tail

        // Du Tran's tutor image guy for fun cuase i was eepy and this was at 3am

        Graphics2D g2d = (Graphics2D) g;

        if (tutorImage != null) {
            g2d.drawImage(tutorImage, 400, 100, 350, 400, null);
        }

        // Draw the message text if it's not empty
        if (!message.isEmpty()) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(message);
            int x = 100 + (400 - textWidth) / 2;
            int y = 200 + ((100 - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(message, x, y);
        }
    }

    // Method to set the message
    public void setMessage(String message) {
        this.message = message;
    }

    //method to set image for win screen reset works
    public void setTutorImage(Image image) {
        this.tutorImage = image;
    }
    //End of Du's work
}
