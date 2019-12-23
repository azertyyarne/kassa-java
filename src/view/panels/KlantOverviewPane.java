package view.panels;

import controller.KlantController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.Collection;

public class KlantOverviewPane extends GridPane {
    private TableView<Product> table;
    private Label labelPrice,labelCosts;
    private KlantController controller;

    public KlantOverviewPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        table = new TableView<>();
        TableColumn<Product, String> colName = new TableColumn<>("Naam");
        colName.setMinWidth(100);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Double> colPrice = new TableColumn<>("Prijs");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Integer> colQuantity = new TableColumn<>("Hoeveelheid");
        colQuantity.setMinWidth(100);
        colQuantity.setCellValueFactory(celldata -> new ReadOnlyObjectWrapper<>(controller.getQuantity(celldata.getValue())));
        table.getColumns().addAll(colName, colPrice, colQuantity);
        labelPrice = new Label();
        labelCosts = new Label();

        showOverview();
    }

    public void showOverview(){
        this.getChildren().clear();
        this.add(table,0,0);
        this.add(labelPrice,1,0);
    }

    public void showAfsluitMenu(){
        this.getChildren().clear();
        this.add(table,0,0);
        this.add(labelCosts,1,0);
    }

    public void setController(KlantController controller){
        this.controller = controller;
    }

    public void refresh(){
        table.refresh();
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

    public Label getLabelPrice() {
        return labelPrice;
    }

    public Label getLabelCosts() {
        return labelCosts;
    }
}
