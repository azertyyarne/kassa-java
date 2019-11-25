package application;
	
import controller.KassaOverviewController;
import controller.ProductOverviewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Kassa;
import view.KassaView;
import view.KlantView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Kassa kassa = new Kassa();

		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		ProductOverviewController productOverviewController = new ProductOverviewController(kassa,kassaView.getProductOverviewPane());
		KassaOverviewController kassaOverviewController = new KassaOverviewController(kassa,kassaView.getKassaOverviewPane());

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
