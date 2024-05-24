package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TowerPanel extends JPanel implements PropertyChangeListener {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.decode("#EFF7F6"));
        for (Tower tower : GameData.getInstance().getTowers()) {
            tower.draw(g);
        }
        for (NewDisk disk : GameData.getInstance().getNewDisks()) {
            disk.draw(g);
        }
    }
    private JButton restartButton;
    private JButton shopButton;

    public TowerPanel() {
        setBackground(Color.decode("#EFF7F6"));
        initializeComponents();
    }

    private void initializeComponents() {
        setBackground(Color.decode("#EFF7F6"));

        // Initialize the shop button
        shopButton = new JButton("Shop");
        shopButton.setBackground(Color.decode("#F7D6E0"));
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showShopPanel();
            }
        });

        restartButton = new JButton("Restart Game");
        restartButton.setBackground(Color.decode("#F7D6E0"));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to restart the game from GameData
                GameData.getInstance().restart();
                repaint();
            }
        });

        this.add(restartButton, BorderLayout.CENTER);
        this.add(shopButton);
        repaint();
    }

    private void showShopPanel() {
        // Create the layered panel for the shop
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());

        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(Color.WHITE);
        shopPanel.setPreferredSize(new Dimension(200, 200));
        shopPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS)); // Vertical BoxLayout

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the layered panel when the exit button is clicked
                removeShopPanel(layeredPane);
            }
        });

        JLabel shopItemsLabel = new JLabel("Tom's Shop");
        JButton item1Button = new JButton("Turn Disks Red (10 coins)");
        JButton item2Button = new JButton("Turn Disks Blue (10 coins)");
        JButton item3Button = new JButton("Turn Disks Green (10 coins)");

        item1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deduct coins and change disk color if user has enough coins
                if (Currency.getInstance().getCoins() >= 10) {
                    Currency.getInstance().deductCoins(10);
                    for (NewDisk disk : GameData.getInstance().getNewDisks()) {
                        disk.setColor(Color.RED);
                    }
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient coins!", "Shop", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deduct coins and change disk color if user has enough coins
                if (Currency.getInstance().getCoins() >= 10) {
                    Currency.getInstance().deductCoins(10);
                    for (NewDisk disk : GameData.getInstance().getNewDisks()) {
                        disk.setColor(Color.BLUE);
                    }
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient coins!", "Shop", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deduct coins and change disk color if user has enough coins
                if (Currency.getInstance().getCoins() >= 10) {
                    Currency.getInstance().deductCoins(10);
                    for (NewDisk disk : GameData.getInstance().getNewDisks()) {
                        disk.setColor(Color.GREEN);
                    }
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient coins!", "Shop", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        shopPanel.add(shopItemsLabel);
        shopPanel.add(Box.createVerticalStrut(10)); // Add some spacing between the title and buttons
        shopPanel.add(item1Button);
        shopPanel.add(item2Button);
        shopPanel.add(item3Button);
        shopPanel.add(Box.createVerticalStrut(10)); // Add some spacing between the buttons and exit button
        shopPanel.add(exitButton);

        layeredPane.add(shopPanel, BorderLayout.CENTER);
        layeredPane.setBounds(50, 50, 200, 200);

        add(layeredPane);
        revalidate();
        repaint();
    }

    private void removeShopPanel(JLayeredPane layeredPane) {
        remove(layeredPane);
        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}