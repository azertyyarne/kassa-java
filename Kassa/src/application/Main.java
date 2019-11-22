package application;
	
import controller.OverviewController;
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

		OverviewController overviewController = new OverviewController(kassa,kassaView.getProductOverview());
	}
	
	public static void main(String[] args) { launch(args); }
}
