package database;

import model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductDB {
    private ProductDBstrategy strategy;

    public void setStrategy(ProductDBstrategy strategy) {
        this.strategy = strategy;
    }

    public ProductDBstrategy getStrategy() {
        return strategy;
    }

    public Collection<Product> getProducts() {
        return strategy.getProducts();
    }

    public Product getProduct(String code){
        return strategy.getProduct(code);
    }

    public void saveProducts(){
        if (strategy instanceof InMemoryDB){
            ((InMemoryDB) strategy).save();
        }
    }
}
