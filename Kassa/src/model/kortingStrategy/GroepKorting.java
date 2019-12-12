package model.kortingStrategy;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public class GroepKorting extends KortingStrategy {
    private String kortingsGroep;

    public void setKortingsGroep(String kortingsGroep) {
        this.kortingsGroep = kortingsGroep;
    }

    @Override
    public double getKorting(ShoppingCart cart) {
        double korting = 0;
        for (Product product : cart.getAllProducts()) {
            if (product.getGroup().equals(kortingsGroep))
                korting+=product.getPrice()*(procent/100);
        }
        return korting;
    }
}
