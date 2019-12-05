package model.kortingStrategy;

import model.Product;

import java.util.List;

public class DrempelKorting implements KortingStrategy {
    @Override
    public double getKorting(List<Product> products) {
        return 0;
    }
}
