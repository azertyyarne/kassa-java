package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panels.KlantOverviewPane;

public class KlantView {
	private Stage stage = new Stage();
	private KlantOverviewPane klantOverviewPane = new KlantOverviewPane();

	public KlantView(){			
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Scene scene = new Scene(klantOverviewPane, 500, 500);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public KlantOverviewPane getKlantOverviewPane() {
		return klantOverviewPane;
	}
}
