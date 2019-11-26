package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import Model.Tickets;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ControllerHomePaciente {
	@FXML
	private JFXListView<Tickets> lvTicketsPaciente = new JFXListView<Tickets>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();

	private ListView<Tickets> listaTickets = new ListView<Tickets>();
	

	@FXML
	public void enviarMSM() { 
		System.out.println(jfxTaPaciente.getText());
		jfxTaPaciente.setText("");
		
		
	}
}