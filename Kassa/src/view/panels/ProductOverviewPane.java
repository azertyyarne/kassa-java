package view.panels;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.Collection;
import java.util.List;

public class ProductOverviewPane extends GridPane {
	private TableView<Product> table;

	public ProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
		this.add(new Label("Products:"), 0, 0, 1, 1);

		table = new TableView<>();
		TableColumn<Product, Integer> colProductcode = new TableColumn<>("Product code");
		colProductcode.setMinWidth(100);
		colProductcode.setCellValueFactory(new PropertyValueFactory<>("code"));
		TableColumn<Product, String> colName = new TableColumn<>("Name");
		colName.setMinWidth(100);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Product, String> colProductgroup = new TableColumn<>("Product group");
		colProductgroup.setMinWidth(100);
		colProductgroup.setCellValueFactory(new PropertyValueFactory<>("group"));
		TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
		colPrice.setMinWidth(100);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Product, Integer> colStock = new TableColumn<>("Stock");
		colStock.setMinWidth(100);
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		table.getColumns().addAll(colProductcode, colName, colProductgroup, colPrice, colStock);
		this.add(table,0,1);
	}

	public void refresh(){
		table.refresh();
	}

	public void setProducts(Collection<Product> products){
		table.setItems(FXCollections.observableArrayList(products));
		setSortType();
	}

	private void setSortType(){
		TableColumn colName = table.getColumns().get(1);
		colName.setSortType(TableColumn.SortType.ASCENDING);
		table.getSortOrder().add(colName);
	}
}
