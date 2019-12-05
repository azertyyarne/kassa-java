package model.kortingStrategy;

import model.Product;
import model.ShoppingCart;

import java.util.List;

public class DuursteKorting implements KortingStrategy {
    private double percent;

    public DuursteKorting(double percent){
        this.percent = percent;
    }

    @Override
    public double getKorting(ShoppingCart cart) {
        return getDuurste(cart.getAllProducts()).getPrice() * (percent / 100);
    }

    private Product getDuurste(List<Product> products){
        products.sort((Product p1,Product p2) -> (int) (p1.getPrice() - p2.getPrice()));
        return products.get(products.size()-1);
    }
}
