package com.codecool;

public class Player {

    String name;
    double money;

    Player(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public double getMoney() {
        return this.money;
    }
}
