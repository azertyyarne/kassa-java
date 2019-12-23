package model;

import database.ProductDB;
import database.ProductDBstrategy;
import model.decorator.DecoratorKassabon;
import model.korting.KortingStrategy;
import model.observer.Observable;
import model.observer.ObserverLogger;
import model.observer.ObserverUpdate;
import model.observer.ObserverVerkoop;

import java.util.*;

public class Kassa implements Observable {
    private ProductDB productDB = new ProductDB();
    private List<Verkoop> verkopen = new ArrayList<>();
    private List<ObserverUpdate> observersUpdate = new ArrayList<>();
    private List<ObserverVerkoop> observersVerkoop = new ArrayList<>();
    private List<ObserverLogger> observersLogger = new ArrayList<>();
    private int maxOnHold = 1;
    private int maxAgeOnHold = 3;
    private KortingStrategy korting;
    private DecoratorKassabon kassabon;

    public void setProductDB(ProductDBstrategy strategy) {
        productDB.setStrategy(strategy);
    }

    public ProductDB getProductDB() {
        return productDB;
    }

    public void setKorting(KortingStrategy korting) {
        this.korting = korting;
    }

    public KortingStrategy getKorting() {
        return korting;
    }

    public void setKassabon(DecoratorKassabon kassabon) {
        this.kassabon = kassabon;
    }

    public Collection<Product> getProductsDB(){
        return productDB.getProducts();
    }

    public Product getProductDB(String code){
        return productDB.getProduct(code);
    }

    @Override
    public void addObserverUpdate(ObserverUpdate observerUpdate){
        observersUpdate.add(observerUpdate);
    }

    @Override
    public void addObserverVerkoop(ObserverVerkoop observerVerkoop){
        observersVerkoop.add(observerVerkoop);
    }

    @Override
    public void addObserverLogger(ObserverLogger observerLogger){
        observersLogger.add(observerLogger);
    }

    public List<Product> getAllProductsOnHold() {
        return getOnHoldVerkoop().getAllProducts();
    }

    public List<Product> getAllProductsVerkoop() {
        return getVerkoop().getAllProducts();
    }

    public List<Product> getProductsVerkoop(){
        return getVerkoop().getProducts();
    }

    public int getQuantityProductVerkoop(Product product){
        return getVerkoop().getQuantity(product);
    }

    public double getTotalPriceVerkoop(){
        return getVerkoop().getTotalPrice();
    }

    public double getTotalKortingVerkoop(){
        return getVerkoop().getKorting();
    }

    public void addProductVerkoop(String code){
        Product product = getProductDB(code);
        getVerkoop().add(product);
        updateObservers();
    }

    public void deleteProductVerkoop(Product product){
        getVerkoop().delete(product);
        updateObservers();
    }

    private Verkoop getVerkoop(){
        for (Verkoop verkoop : verkopen){
            if (verkoop.getState().equals(verkoop.getActiveState())){
                return verkoop;
            }
        }
        Verkoop verkoop = new Verkoop(korting,maxAgeOnHold);
        verkopen.add(verkoop);
        return verkoop;
    }

    private Verkoop getOnHoldVerkoop(){
        for (Verkoop verkoop : verkopen){
            if (verkoop.getState().equals(verkoop.getOnholdState())){
                return verkoop;
            }
        }
        throw new ModelException("Er is geen verkoop on hold");
    }

    public void putOnHold(){
        getVerkoop().putOnHold();
        updateObservers();
    }

    public boolean ableToPutOnHold(){
        return amountOnHold() < maxOnHold;
    }

    public int amountOnHold(){
        int amount = 0;
        for (Verkoop verkoop : verkopen){
            if (verkoop.getState().equals(verkoop.getOnholdState())){
                amount++;
            }
        }
        return amount;
    }

    public void pay(){
        notifyObservers();
        Verkoop verkoop = getVerkoop();
        verkoop.pay();
        print(verkoop);
        increaseAge();
        updateObservers();
    }

    public void cancel(){
        getVerkoop().cancel();
        increaseAge();
        updateObservers();
    }

    public void onHoldToActive(){
        for (Verkoop verkoop : verkopen){
            if (verkoop.getState().equals(verkoop.getOnholdState())){
                verkoop.activate();
                break;
            }
        }
    }

    private void print(Verkoop verkoop){
        System.out.println(kassabon.print(verkoop));
    }

    private void increaseAge(){
        for (Verkoop verkoop : verkopen){
            verkoop.increaseAge();
        }
    }

    @Override
    public void updateObservers() {
        for (ObserverUpdate observer : observersUpdate){
            observer.update();
        }
    }

    @Override
    public void afsluitMenu() {
        for (ObserverVerkoop observer : observersVerkoop){
            observer.afsluitMenu();
        }
    }

    @Override
    public void standardMenu() {
        for (ObserverVerkoop observer : observersVerkoop){
            observer.standardMenu();
        }
    }

    @Override
    public void notifyObservers() {
        for (ObserverLogger observer : observersLogger){
            observer.log();
        }
    }
}
