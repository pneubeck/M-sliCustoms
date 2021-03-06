package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
			AnchorPane root = guiLoader.load();
			Scene scene = new Scene(root,520,500);
			primaryStage.setTitle("Order your custom M�SLI");
			primaryStage.setScene(scene);
			primaryStage.show();
			LoginController controller = (LoginController)guiLoader.getController();
			Order order = new Order();
			order.setTotalAmount((double) 500);
			controller.setOrder(order);
			controller.start();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
