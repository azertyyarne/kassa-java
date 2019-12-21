package controller;

import model.Kassa;
import model.Observer;
import view.panels.ProductOverviewPane;

public class ProductOverviewController implements Observer {
    private Kassa model;
    private ProductOverviewPane view;

    public ProductOverviewController(Kassa model, ProductOverviewPane view){
        this.model = model;
        model.addObserver(this);
        this.view = view;
        update();
    }

    @Override
    public void update() {
        view.setProducts(model.getProducts());
        view.refresh();
    }

    @Override
    public void showAfsluitenMenu() {

    }
}
