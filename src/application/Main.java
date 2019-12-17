package application;
//hola
import java.io.IOException;
import Controller.LoginControler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			e.printStackTrace();
		}
	}

	private void mainWindow() throws IOException {			
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/View/Login.fxml"));
		loader.getClass().getResource("/View/Style.css");
		LoginControler control = new LoginControler();
		loader.setController(control);
		loader.setRoot(content);
		Parent root = loader.load();
		Scene scene = new Scene(root, 800, 600); 		
		Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
		primaryStage.getIcons().add(icon);		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}