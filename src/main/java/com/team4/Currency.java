package com.team4;

public class Currency {
    private static Currency instance;
    private int coins;

    private CoinPanel coinPanel;

    private Currency() {
        coins = 0;
    }

    public static Currency getInstance() {
        if (instance == null) {
            instance = new Currency();
        }
        return instance;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
//        if (coinPanel != null) {
//            coinPanel.updateCoins(coins);
//        }
        updateCoinPanel();
    }

    public void addCoins(int amount) {
        coins += amount;
//        if (coinPanel != null) {
//            coinPanel.updateCoins(coins);
//        }
        updateCoinPanel();
    }

    public void setCoinPanel(CoinPanel coinPanel) {
        this.coinPanel = coinPanel;
        updateCoinPanel();
    }

    public void deductCoins(int amount) {
//        if (coins >= amount) {
//            coins -= amount;
//        } else {
//            System.out.println("Insufficient coins!");
//        }
        if (coins >= amount) {
            coins -= amount;
            updateCoinPanel();
        } else {
            System.out.println("Insufficient coins!");
        }
    }

    private void updateCoinPanel() {
        if (coinPanel != null) {
            coinPanel.updateCoins(coins);
        }
    }
}
