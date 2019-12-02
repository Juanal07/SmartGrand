package Controller;

import java.net.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

public class ControllerHomePaciente {
	@FXML
	private JFXListView<Tickets> lvTicketsPaciente = new JFXListView<Tickets>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();
	@FXML
	private Label idPacienteLabel = new Label();
	@FXML
	private ObservableList<Tickets> ticketsObservableList;
	
	public void enviarMSM() {
		String textoPaciente = jfxTaPaciente.getText();
		String dniPaciente = idPacienteLabel.getText();
		String dniMedico = "";
//		LocalDate date = LocalDate.now();
//		System.out.println(date);
		
		List<Medico> listaMedicoRelacion = GsonGeneral.desserializarJsonAArrayMedico();
		System.out.println("objetos en medicos.json: " + listaMedicoRelacion.size());

		for (Medico medico : listaMedicoRelacion) {
			
			
			ArrayList<String> idPacientes = medico.getDniPacientes();
			
			

			int sizeArray = idPacientes.size();
			int i = 0;
			while (i < sizeArray) {
				if (dniPaciente.equals(idPacientes.get(i))) {
					System.out.println("dni medico: " + medico.getIdMedico());
					System.out.println("nº de pacientes del medico: " + idPacientes.size());
					System.out.println("Dni Paciente Encontrado: " + idPacientes.get(i)+" en la posicion:" + i);
					dniMedico = medico.getIdMedico();
					i = i + sizeArray;
				}
				i++;
			}
		}

		Tickets nuevo = new Tickets(dniPaciente, dniMedico, textoPaciente, "");
		System.out.println(dniPaciente + dniMedico + textoPaciente);
		String ruta = "jsonTickets.json";
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket(); 
		System.out.println(tiquets.size());
		tiquets.add(nuevo);
		System.out.println(nuevo.getIdClinico());

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(tiquets);
		System.out.println(representacionBonita);
		GsonGeneral.EscribirJson(representacionBonita, ruta);
	}

//	public void EnviarTicket(Tickets ticket) {
//		String ruta = "jsonTickets.json";
//		java.util.List<Tickets> lista = GsonGeneral.desserializarJsonAArrayTicket(); 
//		lista.add(ticket);
//		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
//		String representacionBonita = prettyGson.toJson(lista);
//		GsonGeneral.EscribirJson(representacionBonita, ruta);
//	}

//	public List<Tickets> leerTickets(ObservableList<Tickets> ticketsObservableList2) {
//		List<Tickets> listaTickets = GsonGeneral.desserializarJsonAArrayTicket();
//		for (Tickets tickets : listaTickets) {
//			ticketsObservableList2.add(tickets);
//		}
//		return ticketsObservableList2;
//	}

	public void writeText(Persona p) {
		// TODO Auto-generated method stub
		idPacienteLabel.setText(p.getDni());

	}

//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		ObservableList<Tickets> ticketsObservableList = FXCollections.observableArrayList();
//		// ticketsObservableList = (ObservableList<Tickets>)
//		// leerTickets(ticketsObservableList);
//		lvTicketsPaciente.setItems(ticketsObservableList);
//	}
}