package com.team4;

import javax.swing.*;
import java.awt.*;

public class CoinPanel extends JPanel {
    private JLabel coinLabel;

    public CoinPanel() {
        coinLabel = new JLabel("Coins: 0");
        coinLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinLabel.setForeground(Color.YELLOW);

        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(coinLabel);
    }

    public void updateCoins(int coins) {
        coinLabel.setText("Coins: " + coins);
    }
}