package view.panels;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.*; 
import javafx.scene.text.*; 
import javafx.scene.control.cell.*; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import model.Company;
import model.Product;

public class ProductOverview extends VBox {
	private Company company;
    private TableView<Product> table ;
           
	public ProductOverview(Company movieCompany){
		this.company = movieCompany;
		this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
       	Label lblHeading = new Label("Movie Inventory");        
        lblHeading.setFont(new Font("Arial", 20));
        table = new TableView<Product>();        
        table.setItems(movieCompany.loadData()); 
        table.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product product = row.getItem();
                    String movieInfo= product.getName()+" \nRecent price is "+ product.getPrice()+" Euro: ";
        			new MovieDetailView(ProductOverview.this,movieInfo,product);
                }
            });
            return row;
        });
        TableColumn<Product, String> colTitle = new TableColumn<>("Title");
        colTitle.setMinWidth(300);        
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        TableColumn<Product, Integer> colYear = new TableColumn<>("Year");
        colYear.setMinWidth(100);
        colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
        TableColumn<Product, Double> colPrice = new TableColumn<>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        table.getColumns().addAll(colTitle, colYear, colPrice); 
        Button button = new Button("Add dummy film");
		button.setOnAction(new AddDummyFilmHandler());
		Button button2 = new Button("Update price of selected movie\n or doubleclick on movie row");
		button2.setOnAction(new UpdatePriceHandler());
		this.getChildren().addAll(lblHeading, table,button,button2);        
     }
	
	public void displayErrorMessage(String errorMessage){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Information Alert");		 
		alert.setContentText(errorMessage);	 
		alert.show();
	}
	
	public void refresh(){
		table.refresh();
	}	
	    
    class AddDummyFilmHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			company.addDummyProduct();
			TableViewSelectionModel <Product> tsm = table.getSelectionModel();
			tsm.select(company.getAantalProducts());
		}
	}
    
    class UpdatePriceHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			TableViewSelectionModel <Product> tsm = table.getSelectionModel();
			Product movie = tsm.getSelectedItem();
			String movieInfo= movie.getName()+" \nRecent price is "+ movie.getPrice()+" Euro: ";
			new MovieDetailView(ProductOverview.this,movieInfo,movie);
		}
	}    
}
