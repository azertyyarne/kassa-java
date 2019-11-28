package controller;

import model.Kassa;
import model.Observer;
import model.Product;
import view.ErrorAlert;
import view.panels.KassaOverviewPane;

public class KassaOverviewController implements Observer {
    private Kassa model;
    private KassaOverviewPane view;

    public KassaOverviewController(Kassa model, KassaOverviewPane view){
        this.model = model;
        model.addObserver(this);
        this.view = view;
        this.view.setController(this);
        update();
        view.getFieldProductCode().setOnAction(event -> addProductToShoppingCart());
    }

    private void addProductToShoppingCart() {
        try{
            int code = Integer.parseInt(view.getFieldProductCode().getText());
            model.addProductShoppingCart(code);
        }
        catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    public void deleteProductOutOfShoppingCart(Product product) {
        try {
            model.deleteProductShoppingCart(product);
        }
        catch (Exception e){
            ErrorAlert.show(e.getMessage());
        }
    }

    @Override
    public void update() {
        view.setProducts(model.getAllProductsShoppingCart());
        view.getLabelTotalPrice().setText(String.valueOf(model.getTotalPriceShoppingCart()));
        view.refresh();
    }
}
