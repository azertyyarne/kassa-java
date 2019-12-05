package model.kortingStrategy;

import model.ShoppingCart;

public class DrempelKorting implements KortingStrategy {

    private double drempel;
    private double percent;

    public DrempelKorting(double drempel, double procent) {
        this.drempel = drempel;
        this.percent = procent/100;
    }

    @Override
    public double getKorting(ShoppingCart shoppingCart) {
        if (shoppingCart.getTotalPrice() >= drempel) {
            return shoppingCart.getTotalPrice()*percent;
        }
        return 0;
    }
}
