package Controller;

import Model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VistaCuidador2Controller {
	
	@FXML
	public Button atras;

	@FXML
	private Label idCuidadorLabel = new Label();

	public void writeText(Persona p) {
		idCuidadorLabel.setText("Aquí puedes ver información de tu paciente: " + p.getNombre());
	}
	
	@FXML
	public void atras(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) atras.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaCuidadorMain = "/View/VistaCuidador1.fxml";
		String tituloVista = "Cuidador";
		VistaCuidador1Controller cuidadorController = new VistaCuidador1Controller();
		crearVentana(vistaCuidadorMain, tituloVista, cuidadorController);
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
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // a�ade icono a la vista	
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
	
	


