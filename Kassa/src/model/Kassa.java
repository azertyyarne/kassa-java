package model;

import database.ProductDB;
import database.ProductDBstrategy;
import model.kortingStrategy.KortingStrategy;
import java.util.*;

public class Kassa implements Observable {
    private ProductDB productDB = new ProductDB();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private List<Product> onHoldShoppingCart;
    private List<Observer> observers = new ArrayList<>();
    private KortingStrategy kortingStrategy;

    public void setKortingStrategy(KortingStrategy kortingStrategy) {
        this.kortingStrategy = kortingStrategy;
    }

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

    public double getFinalPriceShoppingCart() {
        return shoppingCart.getTotalPrice()-getKorting();
    }

    public double getKorting(){
        return kortingStrategy.getKorting(shoppingCart);
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

    public List<Product> getOnHoldShoppingCart() {
        return onHoldShoppingCart;
    }

    public void deleteOnHoldShoppingCart() {
        this.onHoldShoppingCart = null;
    }

    public void manageOnHoldCart() {
        if (onHoldShoppingCart == null) {
            onHoldShoppingCart = shoppingCart.getAllProducts();
            shoppingCart = new ShoppingCart();
        } else {
            shoppingCart = new ShoppingCart();
            shoppingCart.setProducts(onHoldShoppingCart);
            onHoldShoppingCart = null;
        }
        updateObservers();
    }

    @Override
    public void updateObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }


}
