package database;

import model.Product;

import java.util.Collection;

public interface ProductDBstrategy {
    Collection<Product> getProducts();
    Product getProduct(int code);
}
