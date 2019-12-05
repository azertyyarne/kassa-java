package model.kortingStrategy;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public class GroepKorting implements KortingStrategy {


    private String kortingsGroep;
    private double percentage;

    public GroepKorting(String kortingsGroep , double percentage){
        this.kortingsGroep = kortingsGroep;
        this.percentage = percentage/100;
    }

    @Override
    public double getKorting(ShoppingCart cart) {
        double korting = 0;
        for (Product product : cart.getAllProducts()) {
            if (product.getGroup().equals(kortingsGroep))
                korting+=product.getPrice()*percentage;
        }
        return korting;
    }
}
