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

public class RegistroContinuoControler implements Initializable {
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
		crearVentana(vistaRegContinuo, tituloVista);
	}
	
	@FXML
	public void CuidadorRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnCuidador.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/CuidadorRegistro.fxml";
		String tituloVista = "Registro para cuidadores";
		crearVentana(vistaRegContinuo, tituloVista);
	}
	
	@FXML
	public void clinicoRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnClinico.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/UsuarioRegistro.fxml";
		String tituloVista = "Registro para usuarios";
		crearVentana(vistaRegContinuo, tituloVista);
	}


	public void crearVentana(String vista, String titulo) {
		try {
			FXMLLoader loader = new FXMLLoader();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
