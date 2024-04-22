package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TutorPanel extends JPanel {
    private boolean isImageFlipped = false; // Flag to track the state of the image
    private Timer timer; // Timer for flipping images
    private Image background;

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        // Use the existing 'tutor' instance instead of creating a new one.
        repaint();
        tutor.draw(g);
    }

    //DU Tran stuff to click the tutor for help, reset works
    private Tutor tutor;
    private final Rectangle tutorBounds = new Rectangle(400, 100, 350, 400);

    public TutorPanel() {
        background = Toolkit.getDefaultToolkit().createImage("src/main/resources/gradient1.jpg");
        this.tutor = new Tutor(); // Initialize the tutor once here
        // Add a PropertyChangeListener to listen for changes in gameOver property
        GameData.getInstance().addPropertyChangeListener("gameOver", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int newValue = (int) evt.getNewValue();
                if (newValue == 1) {
                    tutor.setMessage("Congratulations, you win!");
                    startFlippingTimer(); // Start the timer for flipping images
                }
                if (newValue == 0) {
                    tutor.setMessage("I'm Javier, click on me if you need any assistance!");
                    stopFlippingTimer(); // Stop the timer for flipping images
                    Image newImage = loadImage("src/main/resources/tutor1.png"); // Load the new image
                    tutor.setTutorImage(newImage); // Set the new image for the tutor
                    Solver solver = GameData.getInstance().getSolver();
                    GameData repository = GameData.getInstance();
                    solver.resetMoves(); // Reset the nextMoves list back to index 0
                    repository.solveGame(); // Call solveGame() from the Repository instance
                    repaint();
                }
            }
        });

        //initial tutor message
        tutor.setMessage("I'm Javier, click on me if you need any assistance!");
        GameData.getInstance().solveGame(); // game is already pre solve to create a solutions list
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tutorBounds.contains(e.getPoint())) {
                    Solver solver = GameData.getInstance().getSolver();
                    String nextMove = solver.getNextMove(); // Use getNextMove for sequential tips
                    tutor.setMessage(nextMove);
                    repaint();
                }
            }
        });
    }

    // Method to load a new image
    private Image loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(imagePath);
        return ii.getImage();
    }

    // Method to start the timer for flipping images
    private void startFlippingTimer() {
        if (timer == null) {
            timer = new Timer(500, new ActionListener() { // Adjust the interval as needed
                @Override
                public void actionPerformed(ActionEvent e) {
                    flipImage();
                }
            });
            timer.start(); // Start the timer
        }
    }

    // Method to stop the timer for flipping images
    private void stopFlippingTimer() {
        if (timer != null) {
            timer.stop(); // Stop the timer
            timer = null; // Set the timer to null
        }
    }

    // Method to flip the image
    private void flipImage() {
        if (isImageFlipped) {
            Image newImage = loadImage("src/main/resources/javierwin.png"); // Load the new image
            tutor.setTutorImage(newImage); // Set the new image for the tutor
            isImageFlipped = false; // Toggle the flag
        } else {
            Image newImage = loadImage("src/main/resources/javierwin2.png"); // Load the alternate image
            tutor.setTutorImage(newImage); // Set the alternate image for the tutor
            isImageFlipped = true; // Toggle the flag
        }
        repaint();
    }
}