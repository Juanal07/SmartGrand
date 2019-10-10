package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginControler implements Initializable{
	@FXML public ImageView imgLogo;
	@FXML public TextField jfxtUsuario;
	@FXML public PasswordField jfxtPassword;
	@FXML public Button btnIniciarSesion;
	@FXML public Button btnRegistrarse;
	@FXML public Label labelPregunta;
	
	
	@FXML public void  iniciarSesion(ActionEvent actionEvent){
		String usuario = jfxtUsuario.getText();
		String contrasena = jfxtPassword.getText();
		System.out.println("Usuario: " + usuario + " -> Contraseña: " + contrasena);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	

}
