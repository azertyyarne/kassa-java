package view.panels;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Product;


public class KassaOverviewPane extends GridPane {
    protected Label labelProductCode;
    private TextField fieldProductCode;

    public KassaOverviewPane() {
        labelProductCode = new Label("Artikelcode:");
        fieldProductCode = new TextField();
        this.add(labelProductCode, 0, 0, 1, 1);
        this.add(fieldProductCode, 0, 1, 1, 1);
    }

    public void addProduct(Product product){

    }

    public TextField getFieldProductCode() {
        return fieldProductCode;
    }
}
