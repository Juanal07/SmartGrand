package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginControler implements Initializable {
	@FXML
	public ImageView imgLogo;
	@FXML
	public TextField jfxtUsuario;
	@FXML
	public PasswordField jfxtPassword;
	@FXML
	public Button btnIniciarSesion;
	@FXML
	public Button btnRegistrarse;
	@FXML
	public Label labelPregunta;

	@FXML
	public void iniciarSesion(ActionEvent actionEvent) {
		String usuario = jfxtUsuario.getText();
		String contrasena = jfxtPassword.getText();
		System.out.println("Usuario: " + usuario + " -> Contraseña: " + contrasena);
	}

	@FXML
	public void registroContinuo(ActionEvent actionEvent) {
		System.out.println("registro continuo.");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/View/RegistroContinuo.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("RegistroContinuo");
			Scene scene = new Scene(page);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

}
