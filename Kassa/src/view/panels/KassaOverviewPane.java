package view.panels;

import controller.KassaOverviewController;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.Collection;

public class KassaOverviewPane extends GridPane {
    private TableView<Product> table;
    private TextField fieldProductCode;
    private Label labelTotalPrice, labelFinalPrice;
    private KassaOverviewController controller;
    private Button onHoldBtn, afsluitenBtn, betaaldBtn;

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
        colName.setMinWidth(150);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(150);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(colName, colPrice);
        fieldProductCode = new TextField();
        labelTotalPrice = new Label();
        onHoldBtn = new Button("Zet in wachtrij");
        afsluitenBtn = new Button("AFSLUIT");
        afsluitenBtn.setMinWidth(100);
        betaaldBtn = new Button("BETAALD");
        betaaldBtn.setMinWidth(100);
        labelFinalPrice = new Label();
        inputMenu();
    }

    public void inputMenu() {
        this.getChildren().clear();

        this.add(table,0,1,2,10);
        this.add(fieldProductCode,0,0);
        this.add(labelTotalPrice,2,1);
        this.add(onHoldBtn, 2, 0);
        this.add(afsluitenBtn, 0, 11);
    }

    public void afsluitMenu() {
        this.getChildren().clear();
        this.add(table,0,1,2,10);
        this.add(labelFinalPrice, 2, 1, 1, 3);
        this.add(betaaldBtn, 1, 12);
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

    public Label getLabelFinalPrice() {
        return labelFinalPrice;
    }

    public void changeOnHoldBtnText() {
        if (onHoldBtn.getText().equals("Zet in wachtrij"))
            onHoldBtn.setText("Restore cart");
        else
            onHoldBtn.setText("Zet in wachtrij");
    }
}
