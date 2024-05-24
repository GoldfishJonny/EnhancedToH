package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel {
    private JButton shopButton;
    private JLayeredPane layeredPane;
    private JPanel shopItemsPanel;
    private Currency currency;

    public ShopPanel() {
        initializeComponents();
        setupShopItemsPanel();
        currency = Currency.getInstance(); // Initialize currency instance
    }

    private void initializeComponents() {
        setBackground(Color.decode("#EFF7F6"));

        shopButton = new JButton("Shop");
        shopButton.setBackground(Color.decode("#F7D6E0"));
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.setVisible(true);
            }
        });

        // Add shop button to the existing layout
        setLayout(new BorderLayout());
        add(shopButton, BorderLayout.EAST);
    }

    private void setupShopItemsPanel() {
        // Create layered pane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);
        layeredPane.setLayout(null);
        layeredPane.setVisible(false); // Initially hidden

        // Create shop items panel
        shopItemsPanel = new JPanel();
        shopItemsPanel.setBackground(Color.WHITE);
        shopItemsPanel.setBounds(200, 100, 400, 300);
        shopItemsPanel.setLayout(new GridLayout(3, 1));

        // Add shop items buttons
        JButton item1Button = new JButton("Item 1");
        item1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if user has enough coins to purchase Item 1
                if (currency.getCoins() >= 10) {
                    // Deduct 10 coins from user's balance
                    currency.deductCoins(10);
                    // Display message indicating successful purchase
                    JOptionPane.showMessageDialog(ShopPanel.this, "You have purchased Item 1!");
                    // Update coins label (if applicable)
                    // ...
                } else {
                    // Display message indicating insufficient funds
                    JOptionPane.showMessageDialog(ShopPanel.this, "You don't have enough coins to buy this item!");
                }
            }
        });

        JButton item2Button = new JButton("Item 2");
        item1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if user has enough coins to purchase Item 1
                if (currency.getCoins() >= 10) {
                    // Deduct 10 coins from user's balance
                    currency.deductCoins(10);
                    // Display message indicating successful purchase
                    JOptionPane.showMessageDialog(ShopPanel.this, "You have purchased Item 2!");
                    // Update coins label (if applicable)
                    // ...
                } else {
                    // Display message indicating insufficient funds
                    JOptionPane.showMessageDialog(ShopPanel.this, "You don't have enough coins to buy this item!");
                }
            }
        });

        JButton item3Button = new JButton("Item 3");
        item1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if user has enough coins to purchase Item 1
                if (currency.getCoins() >= 10) {
                    // Deduct 10 coins from user's balance
                    currency.deductCoins(10);
                    // Display message indicating successful purchase
                    JOptionPane.showMessageDialog(ShopPanel.this, "You have purchased Item 3!");
                    // Update coins label (if applicable)
                    // ...
                } else {
                    // Display message indicating insufficient funds
                    JOptionPane.showMessageDialog(ShopPanel.this, "You don't have enough coins to buy this item!");
                }
            }
        });

        // Add buttons to shop items panel
        shopItemsPanel.add(item1Button);
        shopItemsPanel.add(item2Button);
        shopItemsPanel.add(item3Button);

        // Create exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setBounds(350, 420, 100, 50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPane.setVisible(false);
            }
        });

        // Add components to layered pane
        layeredPane.add(shopItemsPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(exitButton, JLayeredPane.MODAL_LAYER);

        // Add layered pane to the frame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.add(layeredPane);
    }
}