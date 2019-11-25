package view.panels;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class KassaOverviewPane extends GridPane {
    protected Label labelProductCode;
    protected Label labelTotaalbedrag;
    protected Label totaalbedrag;
    private TextField fieldProductCode;
    protected Button addProductBtn;

    public KassaOverviewPane() {
        labelProductCode = new Label("Artikelcode:");
        labelTotaalbedrag = new Label("Totaalbedrag: ");
        totaalbedrag = new Label("0.00");
        fieldProductCode = new TextField();
        addProductBtn = new Button("OK");
        this.add(labelProductCode, 0, 0);
        this.add(fieldProductCode, 1, 0);
        this.add(addProductBtn, 2, 0);
        this.add(labelTotaalbedrag, 5, 4);
        this.add(totaalbedrag, 6, 4);
    }

    public void setClick(EventHandler click) {
        addProductBtn.setOnAction(click);
    }

    public void addBedrag(Double bedrag) {
        this.totaalbedrag.setText(String.valueOf(Double.parseDouble(this.totaalbedrag.getText()) + bedrag));
    }

    public TextField getFieldProductCode() {
        return fieldProductCode;
    }
}
