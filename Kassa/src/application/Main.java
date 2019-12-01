package application;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Kassa;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	private KassaController controller;

	@Override
	public void start(Stage primaryStage) {
		Kassa kassa = new Kassa();

		controller = new KassaController(kassa);
		controller.setUp();

		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		ProductOverviewController productOverviewController = new ProductOverviewController(kassa,kassaView.getProductOverviewPane());
		KassaOverviewController kassaOverviewController = new KassaOverviewController(kassa,kassaView.getKassaOverviewPane());
		SettingsOverviewController settingsOverviewController = new SettingsOverviewController(kassa,kassaView.getSettingsOverviewPane(), controller.getProperties());

		KlantController klantController = new KlantController(kassa,klantView);
	}

	@Override
	public void stop() throws Exception {
		controller.breakDown();
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
