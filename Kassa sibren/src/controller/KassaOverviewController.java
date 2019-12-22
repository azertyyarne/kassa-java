package controller;

import model.Kassa;
import model.observer.ObserverVerkoop;
import model.observer.ObserverUpdate;
import model.Product;
import view.ErrorAlert;
import view.panels.KassaOverviewPane;

public class KassaOverviewController implements ObserverUpdate, ObserverVerkoop {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        model.addObserverUpdate(this);
        model.addObserverVerkoop(this);
        this.view = view;
        this.view.setController(this);
        setupView();
    }

    private void setupView(){
        update();
        view.getFieldProductCode().setOnAction(event -> addProductToCart());
        view.getButtonPutOnHold().setOnAction(event -> model.putOnHold());
        view.getButtonFinish().setOnAction(event -> model.afsluitMenu());
        view.getButtonCancel().setOnAction(event -> {
            model.cancel();
            if (model.amountOnHold() > 0){
                view.showOnHoldMenu();
                view.setProducts(model.getAllProductsOnHold());
            }
            else model.standardMenu();
        });
        view.getButtonPay().setOnAction(event -> {
            model.pay();
            if (model.amountOnHold() > 0){
                view.showOnHoldMenu();
                view.setProducts(model.getAllProductsOnHold());
            }
            else model.standardMenu();
        });
        view.getButtonGetOnHold().setOnAction(event -> {
            model.onHoldToActive();
            view.setProducts(model.getAllProductsVerkoop());
            model.updateObservers();
            model.standardMenu();
        });
        view.getButtonGetNew().setOnAction(event -> {
            view.setProducts(model.getAllProductsVerkoop());
            model.updateObservers();
            model.standardMenu();
        });
    }

    private void addProductToCart() {
        try{
            String code = view.getFieldProductCode().getText();
            model.addProductVerkoop(code);
            view.getFieldProductCode().setText("");
        }
        catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    public void deleteProductOutOfCart(Product product) {
        try {
            model.deleteProductVerkoop(product);
        }
        catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    @Override
    public void update() {
        view.setProducts(model.getAllProductsVerkoop());
        view.getLabelPrice().setText(String.format("Totale prijs: %.2f euro\n",model.getTotalPriceVerkoop()));
        String pricemessage = String.format("Totale prijs: %.2f euro\n",model.getTotalPriceVerkoop());
        pricemessage += String.format("Totale korting: %.2f euro\n",model.getTotalKortingVerkoop());
        pricemessage += "------------------------------\n";
        pricemessage += String.format("Totaal te betalen: %.2f euro",model.getTotalPriceVerkoop() - model.getTotalKortingVerkoop());
        view.getLabelCosts().setText(pricemessage);
        view.getButtonPutOnHold().setDisable(!model.ableToPutOnHold());
        view.refresh();
    }

    @Override
    public void afsluitMenu() {
        view.showAfsluitMenu();
    }

    @Override
    public void standardMenu() {
        view.showInputMenu();
    }
}
