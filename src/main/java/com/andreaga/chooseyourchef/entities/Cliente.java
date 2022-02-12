package com.andreaga.chooseyourchef.entities;

public class Cliente extends Utente {

    private double budget;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Budget: " + budget + "\n";
    }
}
