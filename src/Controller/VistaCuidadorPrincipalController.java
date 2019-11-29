package Controller;

import Model.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VistaCuidadorPrincipalController {

	@FXML
	private Label idCuidadorLabel = new Label();

	public void writeText(Persona p) {
		idCuidadorLabel.setText("Bienvenido: " + p.getNombre());
	}

}
