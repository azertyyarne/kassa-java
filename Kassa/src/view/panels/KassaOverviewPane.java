package view.panels;

import controller.KassaOverviewController;
import javafx.collections.FXCollections;
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
    private KassaOverviewController controller;
    private Button buttonPutOnHold,buttonFinish,buttonCancel,buttonPay,buttonGetOnHold,buttonGetNew;
    private Label labelPrice;
    private Label labelCosts;


    public KassaOverviewPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(20);
        this.setHgap(5);
        this.add(new Label("Products:"), 0, 0, 1, 1);

        table = new TableView<>();
        table.setRowFactory( value -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    controller.deleteProductOutOfCart(row.getItem());
                }
            });
            return row;
        });
        TableColumn<Product, String> colName = new TableColumn<>("Naam");
        colName.setMinWidth(150);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> colPrice = new TableColumn<>("Prijs");
        colPrice.setMinWidth(150);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(colName, colPrice);
        fieldProductCode = new TextField();
        buttonPutOnHold = new Button("Put on hold");
        buttonFinish = new Button("Afsluit");
        buttonCancel = new Button("Annuleer");
        buttonPay = new Button("Betaald");
        buttonGetOnHold = new Button("On Hold");
        buttonGetNew = new Button("Nieuwe");
        labelPrice = new Label();
        labelCosts = new Label();

        showInputMenu();
    }

    public void showInputMenu(){
        this.getChildren().clear();
        this.add(fieldProductCode,0,0);
        this.add(table,0,1,2,1);
        this.add(labelPrice,4,1);
        this.add(buttonPutOnHold,0,2);
        this.add(buttonFinish,1,2);
    }

    public void showAfsluitMenu(){
        this.getChildren().clear();
        this.add(table,0,0,2,1);
        this.add(labelCosts,2,0);
        this.add(buttonCancel,0,1);
        this.add(buttonPay,1,1);
    }

    public void showOnHoldMenu(){
        this.getChildren().clear();
        this.add(new Label("Wilt u de verkoop on hold of een nieuwe?"),0,0);
        this.add(buttonGetOnHold,1,0);
        this.add(buttonGetNew,2,0);
        this.add(new Label("Verkoop on hold: "),0,1);
        this.add(table,0,2);
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

    public Label getLabelPrice() {
        return labelPrice;
    }

    public Label getLabelCosts() {
        return labelCosts;
    }

    public Button getButtonPutOnHold() {
        return buttonPutOnHold;
    }

    public Button getButtonFinish() {
        return buttonFinish;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonPay() {
        return buttonPay;
    }

    public Button getButtonGetOnHold() {
        return buttonGetOnHold;
    }

    public Button getButtonGetNew() {
        return buttonGetNew;
    }
}
