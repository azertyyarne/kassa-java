package application;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Kassa;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	private SettingsController settingsController;

	@Override
	public void start(Stage primaryStage) {
		Kassa kassa = new Kassa();

		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		settingsController = new SettingsController(kassa,kassaView.getSettingsOverviewPane());
		settingsController.setup();

		ProductOverviewController productOverviewController = new ProductOverviewController(kassa,kassaView.getProductOverviewPane());
		KassaOverviewController kassaOverviewController = new KassaOverviewController(kassa,kassaView.getKassaOverviewPane());

		KlantController klantController = new KlantController(kassa,klantView);
	}

	@Override
	public void stop() throws Exception {
		settingsController.breakDown();
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
