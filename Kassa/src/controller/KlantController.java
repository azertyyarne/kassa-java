package controller;

import model.Kassa;
import model.observer.ObserverVerkoop;
import model.observer.ObserverUpdate;
import model.Product;
import view.panels.KlantOverviewPane;


public class KlantController implements ObserverUpdate, ObserverVerkoop {
    private Kassa model;
    private KlantOverviewPane view;

    public KlantController(Kassa model, KlantOverviewPane view){
        this.model = model;
        model.addObserverUpdate(this);
        model.addObserverVerkoop(this);
        this.view = view;
        this.view.setController(this);
        update();
    }

    public int getQuantity(Product product){
        return model.getQuantityProductVerkoop(product);
    }

    @Override
    public void update() {
        view.setProducts(model.getProductsVerkoop());
        view.getLabelPrice().setText(String.format("Totale prijs: %.2f euro\n",model.getTotalPriceVerkoop()));
        String pricemessage = String.format("Totale prijs: %.2f euro\n",model.getTotalPriceVerkoop());
        pricemessage += String.format("Totale korting: %.2f euro\n",model.getTotalKortingVerkoop());
        pricemessage += "------------------------------\n";
        pricemessage += String.format("Totaal te betalen: %.2f euro",model.getTotalPriceVerkoop() - model.getTotalKortingVerkoop());
        view.getLabelCosts().setText(pricemessage);
        view.refresh();
    }

    @Override
    public void afsluitMenu() {
        view.showAfsluitMenu();
    }

    @Override
    public void standardMenu() {
        view.showOverview();
    }
}
