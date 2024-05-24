package com.team4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel {
    private JButton shopButton;
    private JLayeredPane layeredPane;
    private JPanel shopItemsPanel;

    public ShopPanel() {
        initializeComponents();
        setupShopItemsPanel();
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
        // Add shop items to shopItemsPanel (e.g., buttons representing shop items)

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