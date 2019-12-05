package model.kortingStrategy;

import model.Product;

import java.util.List;

public interface KortingStrategy {
    double getKorting(List<Product> products);
}
