package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import Model.Tickets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ControllerHomePaciente implements Initializable {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("hola mundo");
		//lvTicketsPaciente.getItems().addAll(leerTickets());

	}

	public List<Tickets> leerTickets() {
		List<Tickets> listaTickets = GsonGeneral.desserializarJsonAArrayTicket();
		return listaTickets;
	}
}