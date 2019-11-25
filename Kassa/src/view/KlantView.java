package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.panels.KassaOverviewPane;
import view.panels.KlantOverviewPane;

public class KlantView {
	private Stage stage = new Stage();
	private KlantOverviewPane klantOverviewPane = new KlantOverviewPane();


	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		//
		TabPane tabPane = new TabPane();
		Tab klantTab = new Tab("Winkelkar overzicht", klantOverviewPane);
		tabPane.getTabs().add(klantTab);
		tabPane.prefHeightProperty().bind(scene.heightProperty());
		tabPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(tabPane);
		//
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}
	public KlantOverviewPane getKlantOverviewPane() { return klantOverviewPane; }

}
