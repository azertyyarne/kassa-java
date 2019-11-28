package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Kassa;
import model.Product;

import java.util.*;

public class KlantOverviewPane extends GridPane {

    private TableView<Product> table;
    private Kassa kassa;


    public KlantOverviewPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Products:"), 0, 0, 1, 1);

        table = new TableView<>();

        TableColumn<Product, String> colName = new TableColumn<>("Name");
        colName.setMinWidth(100);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Integer> colAmount = new TableColumn<>("Amount");
        colAmount.setMinWidth(100);
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table.getColumns().addAll(colName, colPrice, colAmount);

        this.add(table,0,1);

    }

    public void refresh(){
        table.refresh();
    }

    public void setProducts(Collection<Product> products) {
        Collection<Product> noDuplicateProducts = new ArrayList<>();

        if (products != null){
            for( Product p : products){
                if (!noDuplicateProducts.contains(p)){
                    noDuplicateProducts.add(p);
                }
            }

            ObservableList<Product> products1 = FXCollections.observableArrayList(noDuplicateProducts);
            table.setItems(FXCollections.observableArrayList(products1));
            setSortType();
        }
    }

    private void setSortType(){
        TableColumn colName = table.getColumns().get(1);
        colName.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(colName);
    }

}
