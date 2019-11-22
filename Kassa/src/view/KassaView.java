package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Product;
import view.panels.ProductOverviewPane;

import java.util.List;

public class KassaView {
	private Stage stage = new Stage();
	private ProductOverviewPane productOverviewPane = new ProductOverviewPane();
		
	public KassaView(){			
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		TabPane tabPane = new TabPane();
		Tab kassaTab = new Tab("Kassa");
		Tab artikelTab = new Tab("Artikelen", productOverviewPane);
		Tab instellingTab = new Tab("Instellingen");// , pane van bepaalde instellingen invoegen achter komma
		Tab logTab = new Tab("Log");// , pane van bepaalde tabs invoegen achter komma
		tabPane.getTabs().add(kassaTab);
		tabPane.getTabs().add(artikelTab);
		tabPane.getTabs().add(instellingTab);
		tabPane.getTabs().add(logTab);
		tabPane.prefHeightProperty().bind(scene.heightProperty());
		tabPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(tabPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public ProductOverviewPane getProductOverview(){
		return productOverviewPane;
	}
}
