package controller;

import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.PersonaNew;
import model.TicketsNew;

public class ControllerVistaTicketPaciente {
	@FXML // fx:id="jfxScrollPaneMedico"
	private JFXScrollPane jfxScrollPaneMedico = new JFXScrollPane(); // Value injected by FXMLLoader

	@FXML // fx:id="lbTextoPaciente"
	private Label lbTextoPaciente; // Value injected by FXMLLoader

	@FXML // fx:id="jfxScrollPanePaciente"
	private JFXScrollPane jfxScrollPanePaciente = new JFXScrollPane(); // Value injected by FXMLLoader

	@FXML // fx:id="lbTextoMedico"
	private Label lbTextoMedico; // Value injected by FXMLLoader

	private Label lbOculto = new Label();
	
	// cargamos con lb ocultos los textos de paciente y clinico
	public void writeText(PersonaNew persona, TicketsNew tickets) {
		lbOculto.setText(persona.toString());
		lbTextoPaciente.setText("PACIENTE: " + tickets.getTexto_Paciente());
		lbTextoMedico.setText("MEDICO: " + tickets.getTexto_Medico());
		jfxScrollPanePaciente.setContent(lbTextoPaciente);
		jfxScrollPaneMedico.setContent(lbTextoMedico);
	}	

}
