package model;

import database.Database;
import database.factory.Factory;

import java.util.*;

public class Kassa implements Observable {
    private Database database;
    private Map<Integer,Product> products = new HashMap<>();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private List<Observer> observers = new ArrayList<>();

    public void setDatabase(String database){
        this.database = Factory.getInstance().getDatabase(database);
    }

    public Database getDatabase() {
        return database;
    }

    public void loadProducts(){
        List<Product> productsList = database.load();
        for (Product product : productsList){
            products.put(product.getCode(),product);
        }
    }

    public void saveProducts(){
        database.save(new ArrayList<>(products.values()));
    }

    public Collection<Product> getProducts(){
        return products.values();
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

    public Product getProduct(int code){
        if (!products.containsKey(code)){
            throw new ModelException("Niet bestaande code voor " + code);
        }
        return products.get(code);
    }

    @Override
    public void updateObservers() {
        for (Observer observer : observers){
            observer.update();
        }
    }
}
