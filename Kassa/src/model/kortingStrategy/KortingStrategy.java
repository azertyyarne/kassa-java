package model.kortingStrategy;

import model.ShoppingCart;

public interface KortingStrategy {
    double getKorting(ShoppingCart cart);
}
