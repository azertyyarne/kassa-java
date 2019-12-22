package model.korting;

import model.Verkoop;

public class Geenkorting extends KortingStrategy {

    @Override
    public double getTotalKorting(Verkoop verkoop) {
        return 0;
    }
}
