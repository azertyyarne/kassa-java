package model;

import database.ProductDB;
import database.ProductDBstrategy;

import java.util.*;

public class Kassa implements Observable {
    private ProductDB productDB = new ProductDB();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private List<Observer> observers = new ArrayList<>();

    public void setProductDB(ProductDBstrategy strategy) {
        productDB.setStrategy(strategy);
    }

    public ProductDB getProductDB() {
        return productDB;
    }

    public Collection<Product> getProducts(){
        return productDB.getProducts();
    }

    public Product getProduct(int code){
        return productDB.getProduct(code);
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public Collection<Product> getAllProductsShoppingCart() {
        return shoppingCart.getAllProducts();
    }

    public Collection<Product> getProductsShoppingCart(){
        return shoppingCart.getProducts();
    }

    public int getQuantityProductShoppingCart(Product product){
        return shoppingCart.getQuantity(product);
    }

    public double getTotalPriceShoppingCart(){
        return shoppingCart.getTotalPrice();
    }

    public void addProductShoppingCart(int code){
        Product product = getProduct(code);
        shoppingCart.add(product);
        updateObservers();
    }

    public void deleteProductShoppingCart(Product product){
        shoppingCart.delete(product);
        updateObservers();
    }

    @Override
    public void updateObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
