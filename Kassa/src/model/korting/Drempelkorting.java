package model.korting;

import model.Verkoop;


public class Drempelkorting extends KortingStrategy{
    private double drempel;

    public void setDrempel(double drempel) {
        this.drempel = drempel;
    }

    @Override
    public double getTotalKorting(Verkoop verkoop) {
        return verkoop.getTotalPrice() > drempel? verkoop.getTotalPrice() * (procent / 100) : 0;
    }
}
