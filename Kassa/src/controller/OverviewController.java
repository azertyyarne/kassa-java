package controller;

import model.Kassa;
import view.panels.ProductOverviewPane;

public class OverviewController {
    private Kassa model;
    private ProductOverviewPane view;

    public OverviewController(Kassa model, ProductOverviewPane view){
        this.model = model;
        this.view = view;
        setProducts();
    }

    private void setProducts(){
        view.setProducts(model.getProducts());
    }
}
