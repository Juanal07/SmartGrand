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
import Controller.GsonGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControllerHomePaciente implements Initializable {
	@FXML
	private JFXListView<String> lvTicketsPaciente = new JFXListView<String>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();
	@FXML
	private Label idPacienteLabel = new Label();
	@FXML
	private ObservableList<String> ticketsObservableList;

	public void enviarMSM() {
		String textoPaciente = jfxTaPaciente.getText();
		String dniPaciente = idPacienteLabel.getText();
		String dniMedico = "";
//		LocalDate date = LocalDate.now();
//		System.out.println(date);
		List<Medico> listaMedicoRelacion = GsonGeneral.desserializarJsonAArrayMedico();
		for (Medico medico : listaMedicoRelacion) {
			ArrayList<String> idPacientes = medico.getDniPacientes();
			int sizeArray = idPacientes.size();
			int i = 0;
			while (i < sizeArray) {
				if (dniPaciente.equals(idPacientes.get(i))) {
					System.out.println("dni medico: " + medico.getIdMedico());
					System.out.println("nº de pacientes del medico: " + idPacientes.size());
					System.out.println("Dni Paciente Encontrado: " + idPacientes.get(i) + " en la posicion:" + i);
					dniMedico = medico.getIdMedico();
					i = i + sizeArray;
				}
				i++;
			}
		}
		Tickets nuevo = new Tickets(dniPaciente, dniMedico, textoPaciente, "");
		EnviarTicket(nuevo);
	}

	public void EnviarTicket(Tickets ticket) {
		String ruta = "jsonTickets.json";
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		tiquets.add(ticket);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(tiquets);
		System.out.println(representacionBonita);
		GsonGeneral.EscribirJson(representacionBonita, ruta);
	}

	public void writeText(Persona p) {
		idPacienteLabel.setText(p.getDni());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets(ticketsObservableList);
		lvTicketsPaciente.setItems(ticketsObservableList);
	}

	private void leerTickets(ObservableList<String> ticketsObservableList2) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (!tickets.getTextoClinico().equals("")) {
				String ticket = "Paciente: " + tickets.getTextoPaciente() + "\n" + "Medico: " + tickets.getTextoClinico(); 
				System.out.println(" ****************************** ");
				ticketsObservableList2.add(ticket);
				System.out.println(ticket);
			}
		}
	}
}