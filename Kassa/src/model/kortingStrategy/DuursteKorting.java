package model.kortingStrategy;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public class DuursteKorting extends KortingStrategy {

    @Override
    public double getKorting(ShoppingCart cart) {
        return getDuurste(cart.getAllProducts()).getPrice() * (procent / 100);
    }

    private Product getDuurste(List<Product> products){
        products.sort((Product p1,Product p2) -> (int) (p1.getPrice() - p2.getPrice()));
        return products.get(products.size()-1);
    }
}
