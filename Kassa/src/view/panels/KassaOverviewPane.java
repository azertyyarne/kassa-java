package view.panels;

import controller.KassaOverviewController;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.Collection;

public class KassaOverviewPane extends GridPane {
    private TableView<Product> table;
    private TextField fieldProductCode;
    private Label labelTotalPrice;
    private KassaOverviewController controller;
    private Button onHoldBtn;

    public KassaOverviewPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Products:"), 0, 0, 1, 1);

        table = new TableView<>();
        table.setRowFactory( value -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product product = row.getItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Ben je zeker dat je product met code " + product.getCode() + " wilt verwijderen?");
                    alert.showAndWait();
                    if (alert.getResult().equals(alert.getButtonTypes().get(0))){
                        controller.deleteProductOutOfShoppingCart(product);
                    }
                }
            });
            return row;
        });
        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(100);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(colName, colPrice);
        fieldProductCode = new TextField();
        labelTotalPrice = new Label();
        this.add(fieldProductCode,0,0);
        this.add(labelTotalPrice,2,0);
        this.add(table,0,1,2,1);

        onHoldBtn = new Button("Zet in wachtrij");
        this.add(onHoldBtn, 3, 0);
    }

    public void onHoldEvent(EventHandler onHold) {
        onHoldBtn.setOnAction(onHold);
    }

    public void refresh(){
        table.refresh();
    }

    public void setController(KassaOverviewController controller) {
        this.controller = controller;
    }

    public void setProducts(Collection<Product> products){
        table.setItems(FXCollections.observableArrayList(products));
        setSortType();
    }

    private void setSortType(){
        TableColumn colName = table.getColumns().get(0);
        colName.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(colName);
    }

    public TextField getFieldProductCode() {
        return fieldProductCode;
    }

    public Label getLabelTotalPrice() {
        return labelTotalPrice;
    }

    public void changeTextBtn() {
        if (onHoldBtn.getText().equals("Zet in wachtrij"))
            onHoldBtn.setText("Restore cart");
        else
            onHoldBtn.setText("Zet in wachtrij");
    }
}
