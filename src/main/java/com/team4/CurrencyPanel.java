package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyPanel extends JPanel {
    private JLabel coinsLabel;
    private int coins;

    public CurrencyPanel() {
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Load the coin image
        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/coin.png"));
        JLabel coinLabel = new JLabel(coinIcon);
        add(coinLabel);

        // Add the text "Coins: "
        JLabel coinsTextLabel = new JLabel("Coins: ");
        add(coinsTextLabel);

        // Add the label to display the current number of coins
        coinsLabel = new JLabel("0");
        add(coinsLabel);

        // Set up a timer to increment the coins count every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coins++;
                coinsLabel.setText(Integer.toString(coins));
            }
        });
        timer.start();
    }

    public int getCoins() {
        return coins;
    }

    public void deductCoins(int amount) {
        coins -= amount;
        coinsLabel.setText(Integer.toString(coins));
    }

    public void incrementCoins(int amount) {
        coins += amount;
        coinsLabel.setText(Integer.toString(coins));
    }
}
