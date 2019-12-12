package controller;

import model.Kassa;
import model.Observer;
import model.Product;
import view.KlantView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class KlantController implements Observer {
    private Kassa model;
    private KlantView view;

    public KlantController(Kassa model,KlantView view){
        this.model = model;
        model.addObserver(this);
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
        view.getLabelFinalPrice().setText("Totale prijs:\t\t"+model.getTotalPriceShoppingCart()+"\nKorting:\t\t\t"+model.getKorting()+"\nPrijs na korting:\t"+model.getFinalPriceShoppingCart());
        view.refresh();
    }
}
