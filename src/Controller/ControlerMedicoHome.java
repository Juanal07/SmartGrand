package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import Model.Persona;
import Model.Tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControlerMedicoHome implements Initializable{
	@FXML
	private JFXListView<String> listaPacientesMedico = new JFXListView<String>();
	@FXML
	private JFXListView<String> listaTicketsSinResponder = new JFXListView<String>();
	@FXML
	private Label labelDniMedico = new Label();
	
	private ObservableList<String> pacientesObservableList;
	
	private ObservableList<String> ticketsObservableList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets(ticketsObservableList);
		listaTicketsSinResponder.setItems(ticketsObservableList);
		
		listaTicketsSinResponder.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println("clicked on " + listaTicketsSinResponder.getSelectionModel().getSelectedItem());
			}
		} );
	}

	private void leerTickets(ObservableList<String> ticketsObservableList2) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (tickets.getTextoClinico().equals("")) {
				String ticket = "Paciente: " + tickets.getTextoPaciente() + "	Medico: " + tickets.getTextoClinico(); 
				System.out.println(" ****************************** ");
				ticketsObservableList2.add(ticket);
				System.out.println(ticket);
			}
		}
	}

	public void writeText(Persona p) {
		labelDniMedico.setText(p.getDni());
	}
}
