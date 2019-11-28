package controller;

import model.Kassa;
import model.Product;
import view.panels.KassaOverviewPane;
import view.panels.KlantOverviewPane;

public class KlantOverviewController {
    private Kassa model;
    private KlantOverviewPane view;

    public KlantOverviewController(Kassa model, KlantOverviewPane view){
        this.model = model;
        this.view = view;


        Product product = new Product(77, "name", "group", 1.1,  1);
        Product product2 = new Product(87, "nme", "grup", 2,  3);
        model.addProduct(product);
        model.addProduct(product);
        model.addProduct(product2);

        /*test*/

        view.setProducts(model.getTicketProducts("klant"));
    }

}
