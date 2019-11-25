package view.panels;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class KassaOverviewPane extends GridPane {
    protected Label labelProductCode;
    private TextField fieldProductCode;
    protected Button addProductBtn;

    public KassaOverviewPane() {
        labelProductCode = new Label("Artikelcode:");
        fieldProductCode = new TextField();
        addProductBtn = new Button("OK");
        this.add(labelProductCode, 0, 0, 1, 1);
        this.add(fieldProductCode, 1, 0, 1, 1);
        this.add(addProductBtn, 2, 0,1,1);
    }

    public void setClick(EventHandler click) {
        addProductBtn.setOnAction(click);
    }

    public TextField getFieldProductCode() {
        return fieldProductCode;
    }
}
