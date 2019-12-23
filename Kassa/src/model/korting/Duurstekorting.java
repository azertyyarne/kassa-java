package model.korting;

import model.Product;
import model.Verkoop;

import java.util.List;

public class Duurstekorting extends KortingStrategy{

    @Override
    public double getTotalKorting(Verkoop verkoop) {
        if (verkoop.getAllProducts().size() == 0){
            return 0;
        }
        List<Product> products = verkoop.getAllProducts();
        products.sort((Product p1,Product p2) -> (int) (p1.getPrice() - p2.getPrice()));
        return products.get(products.size() - 1).getPrice() * (procent / 100);
    }
}
