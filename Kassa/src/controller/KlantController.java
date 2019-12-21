package controller;

import model.Kassa;
import model.observer.Observer;
import model.Product;
import model.observer.ObserverKassaEvents;
import view.KlantView;

public class KlantController implements Observer, ObserverKassaEvents {
    private Kassa model;
    private KlantView view;

    public KlantController(Kassa model, KlantView view){
        this.model = model;
        model.addObserver(this);
        model.addObserverAfsluit(this);
        this.view = view;
        this.view.setController(this);
        update();
    }

    public int getQuantity(Product product){
        return model.getQuantityProductShoppingCart(product);
    }

    @Override
    public void update() {
        view.setProducts(model.getProductsShoppingCart());
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
        view.inputMenu();
    }
}
