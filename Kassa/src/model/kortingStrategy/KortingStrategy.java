package model.kortingStrategy;

import model.ShoppingCart;

public abstract class KortingStrategy {
    protected double procent;

    public void setProcent(double procent) {
        this.procent = procent;
    }

    public abstract double getKorting(ShoppingCart cart);
}
