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
        if (coinPanel != null) {
            coinPanel.updateCoins(coins);
        }
    }

    public void addCoins(int amount) {
        coins += amount;
        if (coinPanel != null) {
            coinPanel.updateCoins(coins);
        }
    }

    public void setCoinPanel(CoinPanel coinPanel) {
        this.coinPanel = coinPanel;
    }
}
