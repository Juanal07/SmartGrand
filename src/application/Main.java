package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private Stage primaryStage;
	@FXML
	public AnchorPane content;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			mainWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void mainWindow() throws IOException {
		// primero cargamos la vista fxml en el FXMLLoader.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/View/Login.fxml"));
		loader.setRoot(content);
		// ahora sobre el panel mypane cargamos la vista que dejamos en el FXMLLoader
		Parent root = loader.load();
		Scene scene = new Scene(root, 800, 500);
		// añade la escena al stage y la titula
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}