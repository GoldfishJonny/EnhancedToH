package com.team4;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * TimeTrialPanel represents a JPanel that displays the current time and the best time achieved.
 * It listens for changes in the best time and updates the display accordingly.
 * It also listens for when the game starts and when it ends, to start and stop the timer.
 */
public class TimePanel extends JPanel implements PropertyChangeListener {
    private final Mode timeTrial;

    /**
     * Constructs a TimeTrialPanel with the specified TimeTrial instance.
     *
     * @param mode The TimeTrial instance to display and monitor for changes.
     */
    public TimePanel(Mode mode) {

        this.timeTrial = mode;
        setLayout(new GridLayout(2, 1));

        JPanel curTimePanel = new JPanel();
        Label timeLabel = new Label("Time: 00:00.000", new Font("Monaco", Font.BOLD, 20), Color.WHITE);
        curTimePanel.setLayout(new GridBagLayout());
        curTimePanel.setBackground(Color.decode("0x508991"));
        curTimePanel.add(timeLabel);

        JPanel bestTimePanel = new JPanel();
        Label bestTimeLabel = new Label("Best Time: 00:00.000", new Font("Monaco", Font.BOLD, 20), Color.WHITE);
        GameData.getInstance().setBestTimeLabel(bestTimeLabel);
        bestTimePanel.setLayout(new GridBagLayout());
        bestTimePanel.setBackground(Color.decode("0x004346"));
        bestTimePanel.add(bestTimeLabel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        add(curTimePanel, constraints);
        add(bestTimePanel, constraints);

        GameData.getInstance().addPropertyChangeListener(this);

        Timer timer = new Timer(100, e -> timeLabel.setText("Time: " + timeTrial.formatElapsedTime()));
        timer.start();
    }

    /**
     * Invoked when a property change is detected.
     * Updates the best time label when the best time changes.
     * Starts the timer when game starts.
     * Stops the timer when game ends.
     *
     * @param evt The property change event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("bestTime")) {
            long bestTime = (long) evt.getNewValue();
            JLabel bestTimeLabel = GameData.getInstance().getBestTimeLabel();
            bestTimeLabel.setText("Best Time: " + GameData.getInstance().formatElapsedTime(bestTime));
        }

        if (evt.getPropertyName().equals("counter")) {
            int counter = (int) evt.getNewValue();
            if (counter == 0) {
                timeTrial.setElapsedTime(0);
            }
        }

        if (evt.getPropertyName().equals("counter")) {
            int counter = (int) evt.getNewValue();
            timeTrial.setElapsedTime(counter);
            System.out.println(counter);
            if (counter == 1) {
                timeTrial.restart();
            }
        }

        if (evt.getPropertyName().equals("gameOver")) {
            int gameOver = (int) evt.getNewValue();
            if (gameOver == 0) {
                timeTrial.stop();
            }
            if (gameOver == 1) {
                long time = timeTrial.stop();
                GameData.getInstance().changeBestTime(time);
            }
        }
    }
}
