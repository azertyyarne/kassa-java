package controller;

import model.Kassa;
import model.ModelException;
import model.Product;
import view.panels.KassaOverviewPane;

public class KassaOverviewController {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        this.view = view;
        view.addEvent(event -> addProduct());
        view.removeEvent(event -> removeProduct());
    }

    private void addProduct(){
        Product product = model.getProduct(Integer.parseInt(view.getFieldProductCode().getText()));
        model.addProduct(product);
        view.addBedrag(product.getPrice());
    }

    private void removeProduct() {
        Product product = model.getProduct(Integer.parseInt(view.getFieldProductCode().getText()));
        model.removeProduct(product);
        view.addBedrag(-product.getPrice());
        /*refresh deel van yarne*/
    }
}
