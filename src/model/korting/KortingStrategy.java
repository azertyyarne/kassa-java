package model.korting;

import model.Verkoop;

public abstract class KortingStrategy {
    protected double procent;

    public void setProcent(double procent) {
        this.procent = procent;
    }

    public abstract double getTotalKorting(Verkoop verkoop);
}
