package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.panels.KassaOverviewPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsOverviewPane;

public class KassaView {
	private Stage stage = new Stage();
	private KassaOverviewPane kassaOverviewPane = new KassaOverviewPane();
	private ProductOverviewPane productOverviewPane = new ProductOverviewPane();
	private SettingsOverviewPane settingsOverviewPane = new SettingsOverviewPane();

	public KassaView(){			
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		TabPane tabPane = new TabPane();
		Tab kassaTab = new Tab("Kassa", kassaOverviewPane);
		Tab artikelTab = new Tab("Artikelen", productOverviewPane);
		Tab instellingTab = new Tab("Instellingen", settingsOverviewPane);// , pane van bepaalde instellingen invoegen achter komma
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

	public KassaOverviewPane getKassaOverviewPane() {
		return kassaOverviewPane;
	}

	public ProductOverviewPane getProductOverviewPane(){
		return productOverviewPane;
	}

	public SettingsOverviewPane getSettingsOverviewPane() {
		return settingsOverviewPane;
	}
}
