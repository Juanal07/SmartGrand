package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Model.Tickets;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResponderTicketMedicoControler {
	@FXML
	private JFXTextArea txAreaMedico = new JFXTextArea();
	@FXML
	private JFXButton btnEnviar = new JFXButton();
	@FXML
	private Label lbTextoPaciente = new Label();
	
	public void enviarRespuesta() {
		String txtMedico = txAreaMedico.getText();
	}
	
	public void writeText(Tickets ticket) {
		lbTextoPaciente.setText(ticket.getTextoPaciente());
		
	}
	
	
}
