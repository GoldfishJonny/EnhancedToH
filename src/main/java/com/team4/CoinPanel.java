package com.team4;

import javax.swing.*;
import java.awt.*;

public class CoinPanel extends JPanel {
    private JLabel coinsLabel;
    private JLabel coinImageLabel;

    public CoinPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        setBackground(Color.decode("#EFF7F6"));
        ImageIcon coinIcon = new ImageIcon(getClass().getResource("/coin.png"));
        coinImageLabel = new JLabel(coinIcon);

        coinsLabel = new JLabel("Coins: 0");
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(coinImageLabel);
        add(coinsLabel);
    }

    public void updateCoins(int coins) {
        coinsLabel.setText("Coins: " + coins);
    }
}
