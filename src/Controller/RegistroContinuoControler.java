package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistroContinuoControler {
	@FXML
	public Button btnUsuario;
	@FXML
	public Button btnCuidador;
	@FXML
	public Button btnClinico;

	@FXML
	public void UsuarioRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnUsuario.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/UsuarioRegistro.fxml";
		String tituloVista = "Registro para usuarios";
		UsuarioRegistroControler usuarioRegistroControler = new UsuarioRegistroControler();
		crearVentana(vistaRegContinuo, tituloVista, usuarioRegistroControler);
	}

	@FXML
	public void atrasLogin(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnUsuario.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/Login.fxml";
		String tituloVista = "Login";
		LoginControler loginControler = new LoginControler();
		crearVentana(vistaRegContinuo, tituloVista, loginControler);
	}

	@FXML
	public void CuidadorRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnCuidador.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/CuidadorRegistro.fxml";
		String tituloVista = "Registro para cuidadores";
		CuidadorRegistroControler cuidadorRegistroControler = new CuidadorRegistroControler();
		crearVentana(vistaRegContinuo, tituloVista, cuidadorRegistroControler);
	}

	@FXML
	public void clinicoRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnClinico.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/ClinicoRegistro.fxml";
		String tituloVista = "Registro para clinicos";
		ClinicoRegistroControler clinicoRegistroControler = new ClinicoRegistroControler();
		crearVentana(vistaRegContinuo, tituloVista, clinicoRegistroControler);
	}

	public void crearVentana(String vista, String titulo, Object object) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource(vista));
			loader.setController(object);
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

}
