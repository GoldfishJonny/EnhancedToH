package com.team4;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {

    private CurrencyPanel currencyPanel;

    public StorePanel(CurrencyPanel currencyPanel) {
        this.currencyPanel = currencyPanel;

        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(3, 1));
        contentPanel.setBackground(Color.WHITE);

        // Add your store items/buttons here
        JButton buyButton1 = new JButton("Buy Item 1 (10 Coins)");
        JButton buyButton2 = new JButton("Buy Item 2");
        JButton buyButton3 = new JButton("Buy Item 3");

        // Add action listeners to the buttons
        buyButton1.addActionListener(e -> {
            if (currencyPanel.getCoins() >= 10) {
                // Deduct 10 coins from the currency panel
                currencyPanel.deductCoins(10);
                // Change all disk colors to yellow
                for (NewDisk disk : GameData.getInstance().getNewDisks()) {
                    disk.setColor(Color.YELLOW);
                }
                // Repaint the game panel to reflect the changes
                GameData.getInstance().repaint();
                JOptionPane.showMessageDialog(this, "Items purchased successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient coins!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        contentPanel.add(buyButton1);
        contentPanel.add(buyButton2);
        contentPanel.add(buyButton3);

        add(contentPanel, BorderLayout.CENTER);
    }

    public void buyItem1() {
        // Simulate purchasing item 1
        int itemPrice = 10;
        int currentCoins = currencyPanel.getCoins();

        if (currentCoins >= itemPrice) {
            currencyPanel.deductCoins(itemPrice);
            // Implement the logic to apply the effect of buying item 1
            System.out.println("Item 1 purchased successfully!");
        } else {
            System.out.println("Insufficient coins to buy Item 1!");
        }
    }
}

