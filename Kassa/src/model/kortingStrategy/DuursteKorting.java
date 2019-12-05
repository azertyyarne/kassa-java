package model.kortingStrategy;

import model.Product;

import java.util.List;

public class DuursteKorting implements KortingStrategy {
    @Override
    public double getKorting(List<Product> products) {
        return 0;
    }
}
