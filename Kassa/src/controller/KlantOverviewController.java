package controller;

import model.Kassa;
import view.panels.KassaOverviewPane;
import view.panels.KlantOverviewPane;

public class KlantOverviewController {
    private Kassa model;
    private KlantOverviewPane view;

    public KlantOverviewController(Kassa model, KlantOverviewPane view){
        this.model = model;
        this.view = view;
        setProducts();
    }

    private void setProducts(){
        //view.setProducts(model.getProducts());
        /*met de key "klant" producten lijst doorgeven en extra functie addproducts ook plaatsen zoals kassa*/
    }
}
