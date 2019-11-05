package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClinicoRegistroControler {
	
	@FXML
	public Button btnAtras;
	@FXML
	public Button btnRegistrarse;
	@FXML
	public TextField tfNombre, tfApellido, tfCorreo, tfDni, tfEspecialidad, tfNacionalidad, tfCentroMedico, tfLicencia;
	
	public DatePicker dpFecha;
	
	
	@FXML
	public void atrasRegistroContinuo(ActionEvent actionEvent) {
		// esta line es para cerrar la ventana anterior
		Stage stage = (Stage) btnAtras.getScene().getWindow();
		stage.close();
		// creamos la ventana
		String vistaRegContinuo = "/View/RegistroContinuo.fxml";
		String tituloVista = "RegistroContinuo";
		RegistroContinuoControler registroContinuoControler = new RegistroContinuoControler();
		crearVentana(vistaRegContinuo, tituloVista, registroContinuoControler);

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
