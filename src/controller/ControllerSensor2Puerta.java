package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Persona;
import model.Sensor2puerta;

public class ControllerSensor2Puerta {
	
	@FXML
	private Label idEstadoPuertaLabel = new Label();
	
	public void writeText(Persona p) {
		idEstadoPuertaLabel.setText("Tu paciente "+ p.getNombre() + " tiene actualmente la puerta: " + Sensor2puerta.comoEsta(p.getDni()));
	}
}
