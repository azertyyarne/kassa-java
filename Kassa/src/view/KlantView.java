package view;

import controller.KlantController;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Product;

import java.util.Collection;

public class KlantView {
	private Stage stage = new Stage();
	private TableView<Product> table;
	private Label labelTotalPrice;
	private KlantController controller;

	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Scene scene = new Scene(getRoot(), 500, 500);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private Pane getRoot(){
		GridPane root = new GridPane();
		root.setPadding(new Insets(5, 5, 5, 5));
		root.setVgap(5);
		root.setHgap(5);
		table = new TableView<>();
		TableColumn<Product, String> colName = new TableColumn<>("Name");
		colName.setMinWidth(100);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
		colPrice.setMinWidth(100);
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		TableColumn<Product, Integer> colQuantity = new TableColumn<>("Quantity");
		colQuantity.setMinWidth(100);
		colQuantity.setCellValueFactory(celldata -> {
			Product product = celldata.getValue();
			return new ReadOnlyObjectWrapper<>(controller.getQuantity(product));
		});
		table.getColumns().addAll(colName, colPrice, colQuantity);
		labelTotalPrice = new Label();
		root.add(table,0,1);
		root.add(labelTotalPrice,1,0);
		return root;
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
		TableColumn colName = table.getColumns().get(1);
		colName.setSortType(TableColumn.SortType.ASCENDING);
		table.getSortOrder().add(colName);
	}

	public Label getLabelTotalPrice() {
		return labelTotalPrice;
	}
}
