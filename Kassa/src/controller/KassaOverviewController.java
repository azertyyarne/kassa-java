package controller;

import model.Kassa;
import view.panels.KassaOverviewPane;

public class KassaOverviewController {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        this.view = view;
        view.setClick(event -> addProduct());
    }

    private void addProduct(){
        int id = Integer.parseInt(view.getFieldProductCode().getText());
        model.addProduct(id);
        view.addBedrag(model.getProduct(id).getPrice());
    }
}
