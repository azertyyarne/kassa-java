package view;

import controller.KlantController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Collection;
import model.Product;

public class KlantView {
	private Stage stage = new Stage();
	private TableView<Product> table;
	private Label labelTotalPrice, labelFinalPrice;
	private KlantController controller;
	private GridPane root;

	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		this.root = getRoot();
		Scene scene = new Scene(root, 500, 500);
		inputMenu();
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private GridPane getRoot(){
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
		root.add(table,0,0, 2, 10);
		labelTotalPrice = new Label();
		labelFinalPrice = new Label();
		return root;
	}

	public void inputMenu() {
		root.getChildren().remove(labelFinalPrice);
		this.root.add(labelTotalPrice,2,1);
	}

	public void afsluitMenu() {
		root.getChildren().remove(labelTotalPrice);
		root.add(labelFinalPrice, 2, 1);
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

	public Label getLabelTotalPrice() {
		return labelTotalPrice;
	}

	public Label getLabelFinalPrice() {
		return labelFinalPrice;
	}
}
