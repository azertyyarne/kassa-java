package model;

import database.ProductDB;
import database.ProductDBstrategy;
import model.kortingStrategy.KortingStrategy;
import model.observer.ObserverKassaEvents;
import model.observer.Observable;
import model.observer.Observer;
import java.util.*;

public class Kassa implements Observable, ObserverKassaEvents {
    private ProductDB productDB = new ProductDB();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private List<Product> onHoldShoppingCart;
    private List<Observer> observers = new ArrayList<>();
    private List<ObserverKassaEvents> observerKassaEvents = new ArrayList<>();
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

    public void addObserverAfsluit(ObserverKassaEvents observerAfsluit){
        observerKassaEvents.add(observerAfsluit);
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

    public void resetProductShoppingCart() {
        this.shoppingCart = new ShoppingCart();
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
            if (shoppingCart.getAllProducts().size() == 0) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setProducts(onHoldShoppingCart);
                onHoldShoppingCart = null;
            } else {
                throw new ModelException("Je moet de vorige bestelling eerst afronden vooraleer een oude bestelling terug te zetten");
            }
        }
        updateObservers();
    }

    public void manageAnnuleer() {
        for (Product product : shoppingCart.getAllProducts()) {
            product.moveToStock();
        }
        manageNewEmptyScreen();
    }

    @Override
    public void updateObservers() {
        for (model.observer.Observer observer : observers){
            observer.update();
        }
    }

    @Override
    public void showAfsluitenMenu() {
        for (ObserverKassaEvents observerChangeScreen: observerKassaEvents) {
            observerChangeScreen.showAfsluitenMenu();
        }
    }

    @Override
    public void manageNewEmptyScreen() {
        for (ObserverKassaEvents observerChangeScreen: observerKassaEvents) {
            observerChangeScreen.manageNewEmptyScreen();
        }
    }
}
