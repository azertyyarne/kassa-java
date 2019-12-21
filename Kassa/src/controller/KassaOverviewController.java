package controller;

import model.*;
import model.observer.Observer;
import model.observer.ObserverKassaEvents;
import view.*;
import view.panels.KassaOverviewPane;

public class KassaOverviewController implements Observer, ObserverKassaEvents {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        model.addObserver(this);
        model.addObserverAfsluit(this);
        this.view = view;
        this.view.setController(this);
        view.getFieldProductCode().setOnAction(event -> addProductToShoppingCart());
        view.onHoldEvent(event -> manageOnHoldCart());
        view.afsluitenEvent(event -> model.showAfsluitenMenu());
        view.betalenEvent(event -> model.manageNewEmptyScreen());
        view.annulerenEvent(event -> model.manageAnnuleer()); // todo
        update();
    }

    private void manageOnHoldCart() {
        try {
            model.manageOnHoldCart();
            view.changeOnHoldBtnText();
        } catch (Exception e) {
            ErrorAlert.show(e.getMessage());
        }
    }

    private void addProductToShoppingCart() {
        try {
            int code = Integer.parseInt(view.getFieldProductCode().getText());
            model.addProductShoppingCart(code);
        } catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    public void deleteProductOutOfShoppingCart(Product product) {
        try {
            model.deleteProductShoppingCart(product);
        } catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    @Override
    public void update() {
        view.setProducts(model.getAllProductsShoppingCart());
        view.getLabelTotalPrice().setText("Totale prijs:\t\t"+model.getTotalPriceShoppingCart());
        view.refresh();
    }

    @Override
    public void showAfsluitenMenu() {
        view.getLabelFinalPrice().setText("Totale prijs:\t\t"+model.getTotalPriceShoppingCart()+"\nKorting:\t\t\t"+model.getKorting()+"\nPrijs na korting:\t"+model.getFinalPriceShoppingCart());
        view.afsluitMenu();
    }

    @Override
    public void manageNewEmptyScreen() {
        model.resetProductShoppingCart();
        view.inputMenu();
        update();
    }
}
