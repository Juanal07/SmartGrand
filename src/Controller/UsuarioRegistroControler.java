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

public class UsuarioRegistroControler implements Initializable {
	@FXML
	public Button btnAtras;

	@FXML
	public void atrasRegContinuo(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnAtras.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/RegistroContinuo.fxml";
		String tituloVista = "Registro continuo";
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
