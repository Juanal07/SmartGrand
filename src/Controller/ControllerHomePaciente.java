package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import Model.Medico;
import Model.Persona;
import Model.Tickets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControllerHomePaciente implements Initializable {
	@FXML
	private JFXListView<Tickets> lvTicketsPaciente = new JFXListView<Tickets>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();
	@FXML
	private Label idPacienteLabel = new Label();
	
	private ListView<Tickets> listaTickets = new ListView<Tickets>();

	@FXML
	public void enviarMSM() {
		String textoPaciente = jfxTaPaciente.getText();
		// cojo el dni
		String dniPaciente = idPacienteLabel.getText();
		java.util.List <Medico> listaMedicoRelacion = GsonGeneral.desserializarJsonAArrayMedico();
		for (Medico medico : listaMedicoRelacion) {
			System.out.println("dni medico: " + medico.getIdMedico());
			ArrayList<String> idPacientes = medico.getDniPacientes();
			for (String id : idPacientes) {
				if (id.equals(dniPaciente)) {
					System.out.println("dni encontrado.");
					Tickets ticket = new Tickets(dniPaciente, medico.getIdMedico(), textoPaciente, "");
					
					List<Tickets> lista = GsonGeneral.desserializarJsonAArrayTicket();
					lista.add(ticket); 
					Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
					String representacionBonita = prettyGson.toJson(lista);
					String ruta = "jsonTickets.json";
					GsonGeneral.EscribirJson(representacionBonita, ruta);
				
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("hola mundo");
		/*
		ArrayList<Tickets> tickets = new ArrayList<Tickets>();
		tickets = (ArrayList<Tickets>) leerTickets();
		for (Tickets tickets2 : tickets) {
			System.out.println(tickets2.getIdPaciente());
		}
	*/
	}

	public List<Tickets> leerTickets() {
		List<Tickets> listaTickets = GsonGeneral.desserializarJsonAArrayTicket();
		return listaTickets;
	}

	public void writeText(Persona p) {
		// TODO Auto-generated method stub
		idPacienteLabel.setText(p.getDni());
		
	}
}