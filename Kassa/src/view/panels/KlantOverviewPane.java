package view.panels;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.List;

public class KlantOverviewPane extends GridPane {

    private TableView<Product> table;       /*misschien andere naam dan in productOverviewPane*/

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

}
