package model.kortingStrategy;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public interface KortingStrategy {
    double getKorting(ShoppingCart cart);
}
