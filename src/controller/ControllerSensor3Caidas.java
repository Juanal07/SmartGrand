package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Persona;
import model.Sensor2puerta;

public class ControllerSensor3Caidas {
	
	@FXML
	private Label lbOculto = new Label();	
	@FXML
	private Label idEstadoPuertaLabel = new Label();
	
	public void writeText(Persona p) {
		idEstadoPuertaLabel.setText("Tu paciente X tiene actualmente la puerta: " + Sensor2puerta.comoEsta(p.getDni()));
		lbOculto.setText(p.getDni());
	}
}
