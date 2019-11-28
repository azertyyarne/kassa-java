package model;

import java.util.*;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void add(Product product){
        product.getFromStock();
        products.add(product);
    }

    public void delete(Product product){
        product.moveToStock();
        products.remove(product);
    }

    public int getQuantity(Product product){
        int quantity = 0;
        for (Product p : products){
            if (p.equals(product)){
                quantity++;
            }
        }
        return quantity;
    }

    public List<Product> getAllProducts() {
        return products;

    }

    public double getTotalPrice() {
        double price = 0;
        for (Product product : products){
            price += product.getPrice();
        }
        return price;
    }

    // gives on of each kind of product
    public List<Product> getProducts(){
        List<Product> result = new ArrayList<>();
        for (Product product : products){
            if (!result.contains(product)){
                result.add(product);
            }
        }
        return result;
    }
}
