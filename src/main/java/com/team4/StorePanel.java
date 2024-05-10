package com.team4;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {

    public StorePanel() {
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
        JButton buyButton1 = new JButton("Buy Item 1");
        JButton buyButton2 = new JButton("Buy Item 2");
        JButton buyButton3 = new JButton("Buy Item 3");

        contentPanel.add(buyButton1);
        contentPanel.add(buyButton2);
        contentPanel.add(buyButton3);

        add(contentPanel, BorderLayout.CENTER);
    }
}
