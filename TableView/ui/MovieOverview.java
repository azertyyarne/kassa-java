package ui;

import domain.Movie;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.*; 
import javafx.scene.text.*; 
import javafx.scene.control.cell.*; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import domain.MovieCompany;

public class MovieOverview extends VBox{	
	private MovieCompany movieCompany ;
    private TableView<Movie> table ;
           
	public MovieOverview (MovieCompany movieCompany){
		this.movieCompany = movieCompany;
		this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
       	Label lblHeading = new Label("Movie Inventory");        
        lblHeading.setFont(new Font("Arial", 20));
        table = new TableView<Movie>();        
        table.setItems(movieCompany.loadData()); 
        table.setRowFactory( tv -> {
            TableRow<Movie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Movie movie = row.getItem();
                    String movieInfo= movie.getTitle()+" \nRecent price is "+ movie.getPrice()+" Euro: ";
        			new MovieDetailView(MovieOverview.this,movieInfo,movie);	
                }
            });
            return row;
        });
        TableColumn<Movie, String> colTitle = new TableColumn<Movie, String>("Title");
        colTitle.setMinWidth(300);        
        colTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
        TableColumn<Movie, Integer> colYear = new TableColumn<Movie, Integer>("Year");
        colYear.setMinWidth(100);
        colYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Year"));
        TableColumn<Movie, Double> colPrice = new TableColumn<Movie, Double>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<Movie, Double>("Price"));
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
			movieCompany.addDummyMovie();
			TableViewSelectionModel <Movie> tsm = table.getSelectionModel();
			tsm.select(movieCompany.getAantalMovies());
		}
	}
    
    class UpdatePriceHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			TableViewSelectionModel <Movie> tsm = table.getSelectionModel();
			Movie movie = tsm.getSelectedItem();
			String movieInfo= movie.getTitle()+" \nRecent price is "+ movie.getPrice()+" Euro: ";
			new MovieDetailView(MovieOverview.this,movieInfo,movie);		
		}
	}    
}
