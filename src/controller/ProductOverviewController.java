package controller;

import model.Kassa;
import model.observer.ObserverUpdate;
import view.panels.ProductOverviewPane;

public class ProductOverviewController implements ObserverUpdate {
    private Kassa model;
    private ProductOverviewPane view;

    public ProductOverviewController(Kassa model, ProductOverviewPane view){
        this.model = model;
        model.addObserverUpdate(this);
        this.view = view;
        update();
    }

    @Override
    public void update() {
        view.setProducts(model.getProductsDB());
        view.refresh();
    }
}