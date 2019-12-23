package model;

import model.korting.KortingStrategy;
import model.state.*;

import java.util.*;

public class Verkoop {
    private List<Product> products = new ArrayList<>();
    private VerkoopState onhold = new OnHoldState(this);
    private VerkoopState active = new ActiveState(this);
    private VerkoopState payed = new PayedState(this);
    private VerkoopState cancelled = new CancelledState(this);
    private VerkoopState state = active;
    private KortingStrategy korting;
    private int maxAgeOnHold,ageOnHold;


    public Verkoop(KortingStrategy korting,int maxAgeOnHold){
        this.korting = korting;
        this.maxAgeOnHold = maxAgeOnHold;
    }

    public void add(Product product){
        products.add(product);
    }

    public void delete(Product product){
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

    public double getTotalPrice() {
        double price = 0;
        for (Product product : products){
            price += product.getPrice();
        }
        return price;
    }

    public double getKorting(){
        return korting.getTotalKorting(this);
    }

    public double getBTW(){
        return getTotalPrice() * 0.06;
    }

    private void removeFromStock(){
        for (Product product : products){
            product.removeFromStock();
        }
    }

    public void putOnHold(){
        state.putOnHold();
    }

    public void activate(){
        state.activate();
    }

    public void pay(){
        state.pay();
        removeFromStock();
    }

    public void cancel(){
        state.cancel();
    }

    public void setState(VerkoopState state){
        this.state = state;
    }

    public VerkoopState getState() {
        return state;
    }

    public VerkoopState getActiveState() {
        return active;
    }

    public VerkoopState getOnholdState() {
        return onhold;
    }

    public VerkoopState getPayedState() {
        return payed;
    }

    public VerkoopState getCancelledState(){
        return cancelled;
    }

    public void increaseAge(){
        if (state.equals(onhold)){
            ageOnHold++;
            if (ageOnHold == maxAgeOnHold){
                cancel();
            }
        }
        else ageOnHold = 0;
    }
}
