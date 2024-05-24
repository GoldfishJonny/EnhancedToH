package com.team4;

public class Currency {
    private int coins;

    public Currency() {
        this.coins = 0;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }
}


