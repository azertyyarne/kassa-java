package model.kortingStrategy;

import model.ShoppingCart;

public class DrempelKorting extends KortingStrategy {
    private double drempel;

    public void setDrempel(double drempel) {
        this.drempel = drempel;
    }

    @Override
    public double getKorting(ShoppingCart shoppingCart) {
        if (shoppingCart.getTotalPrice() >= drempel) {
            return shoppingCart.getTotalPrice()*(procent/100);
        }
        return 0;
    }
}
