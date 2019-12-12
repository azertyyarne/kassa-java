package model.kortingStrategy;

import model.ShoppingCart;

public class GeenKorting extends KortingStrategy {

    @Override
    public double getKorting(ShoppingCart cart) {
        return 0;
    }
}
