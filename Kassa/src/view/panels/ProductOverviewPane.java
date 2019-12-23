package view.panels;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Product;

import java.util.Collection;

public class ProductOverviewPane extends GridPane {
	private TableView<Product> table;

	public ProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
		table = new TableView<>();
		TableColumn<Product, String> colProductcode = new TableColumn<>("Code");
		colProductcode.setMinWidth(147);
		colProductcode.setCellValueFactory(new PropertyValueFactory<>("code"));
		TableColumn<Product, String> colName = new TableColumn<>("Naam");
		colName.setMinWidth(147);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Product, String> colProductgroup = new TableColumn<>("Groep");
		colProductgroup.setMinWidth(148);
		colProductgroup.setCellValueFactory(new PropertyValueFactory<>("group"));
		TableColumn<Product, Double> colPrice = new TableColumn<>("Prijs");
		colPrice.setMinWidth(148);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Product, Integer> colStock = new TableColumn<>("Hoeveelheid");
		colStock.setMinWidth(148);
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
