package controller;

import model.Kassa;
import view.panels.KassaOverviewPane;

public class KassaOverviewController {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        this.view = view;
        addProduct();
    }

    private void addProduct(){
        model.addProduct(Integer.parseInt(view.getFieldProductCode().getText()));
    }
}
