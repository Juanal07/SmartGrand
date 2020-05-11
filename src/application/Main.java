package application;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;

import controller.ControllerLogin;
import dataBase.Conexion;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Main extends Application {


	private Stage primaryStage;

	@FXML
	public AnchorPane content;


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			Conexion conexion = new Conexion();
			conexion.crearDb(conexion);
			mainWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mainWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/view/Login.fxml"));
		loader.getClass().getResource("/view/Style.css");
		ControllerLogin control = new ControllerLogin();
		loader.setController(control);
		loader.setRoot(content);
		Parent root = loader.load();
		FadeTransition ft = new FadeTransition(Duration.millis(4500), root);
		ft.setFromValue(0.2);
		ft.setToValue(1.0);
		ft.play();

//		WebView webView = new WebView();
//		webView.getEngine().load("http://google.com");
//		VBox vBox = new VBox(webView);


		Scene scene = new Scene(root);
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