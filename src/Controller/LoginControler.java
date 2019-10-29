package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
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
	public TextField jfxtUsuario = new TextField();
	@FXML
	public PasswordField jfxtPassword = new PasswordField();
	@FXML
	public Button btnIniciarSesion = new Button();
	@FXML
	public Button btnRegistrarse = new Button();
	@FXML
	public Label labelPregunta;

	@FXML
	public void iniciarSesion(ActionEvent actionEvent) {
		/*if (jfxtUsuario.getText().equals("") || jfxtPassword.getText().equals("")) {
			System.out.println("ERROR:Vuleva a introducir los datos.");
			//jfxtUsuario;
		}
		*/
		String usuario = jfxtUsuario.getText();
		String contrasena = jfxtPassword.getText();
		System.out.println("Usuario: " + usuario + " -> Contraseña: " + contrasena);
		jfxtUsuario.setText("Hola mundo java fx");
		validation(usuario);
	}

	@FXML
	public void registroContinuo(ActionEvent actionEvent) {
		// esta line es para cerrar la ventana anterior
	    Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
		stage.close();
		// creamos la ventana
		String vistaRegContinuo = "/View/RegistroContinuo.fxml";
		String tituloVista = "RegistroContinuo";
		Stage stageRegistroContinuo = new Stage();
		crearVentana(vistaRegContinuo, tituloVista, stageRegistroContinuo);
	}

	public void crearVentana(String vista, String titulo, Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			//AnchorPane root = (AnchorPane) loader.load(getClass().getResource(vista).openStream());
			loader.setLocation(this.getClass().getResource(vista));
			AnchorPane page = (AnchorPane) loader.load();
			Stage sendStage = new Stage();
			sendStage.setTitle(titulo);
			Scene scene = new Scene(page);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void validation(String cadena) {
		if (cadena.trim().equals("")) {
			System.out.println("No existe cadena");
		} else {
			System.out.println("Si existe cadena");
		}
	}

	public boolean pedirSoloCadena(String cadena) {
		try {
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Cargando pantalla login...");

		
		
	}

}
