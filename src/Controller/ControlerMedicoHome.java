package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import Model.Medico;
import Model.Persona;
import Model.Tickets;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControlerMedicoHome {
	@FXML
	private JFXListView<Persona> listaPacientesMedico = new JFXListView<Persona>();
	@FXML
	private JFXListView<Tickets> listaTicketsSinResponder = new JFXListView<Tickets>();

	private ObservableList<Persona> pacientesObservableList;

	private ObservableList<Tickets> ticketsObservableList;

	public void leerPersonas(ObservableList<Persona> pacientesObservableList2, Persona p) {
		ArrayList<String> listaMisPacientes;
		listaMisPacientes = listaPersonas(p);
		List<Persona> lista = GsonGeneral.desserializarJsonAArray();
		int lenthArray = listaMisPacientes.size();
		// recorremos personas
		for (int i = 0; i < lista.size(); i++) {
			int j = 0;
			// recorremos mis pacientes
			while (j < lenthArray) {
				if (lista.get(i).getDni().equals(listaMisPacientes.get(j))) {
					pacientesObservableList2.add(lista.get(i));
					j = j + lenthArray;
				}
				j++;
			}
			i++;
		}
	}

	public ArrayList<String> listaPersonas(Persona p) {
		ArrayList<String> idsPacientes = new ArrayList<String>();
		List<Medico> listaMedicoRelacion = GsonGeneral.desserializarJsonAArrayMedico();
		int sizeArray = listaMedicoRelacion.size();
		int i = 0;
		while (i < sizeArray) {
			if (p.getDni().equals(listaMedicoRelacion.get(i).getIdMedico())) {
				idsPacientes = listaMedicoRelacion.get(i).getDniPacientes();
				i = i + sizeArray;
			}
			i++;
		}
		return idsPacientes;
	}

	public void enviarVentana(Tickets ticket) {
		String vistaPaciente = "/View/ResponderTicketMedico.fxml";
		String tituloVista = "responder tiquet.";

		Stage stage = (Stage) listaTicketsSinResponder.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ResponderTicketMedicoControler responderTicketMedicoControler = new ResponderTicketMedicoControler();
			loader.setController(responderTicketMedicoControler);
			Parent root2 = loader.load();
			responderTicketMedicoControler.writeText(ticket);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root2));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void leerTickets(ObservableList<Tickets> ticketsObservableList2) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (tickets.getTextoClinico().equals("")) {
				ticketsObservableList2.add(tickets);
			}
		}
	}

	public void cargarListViewPacientes(Persona p) {
		ObservableList<Persona> personasObservableList = FXCollections.observableArrayList();
		leerPersonas(personasObservableList, p);
		listaPacientesMedico.setItems(personasObservableList);

		listaPacientesMedico.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("hola huele bicho XD");
			}

		});

	}

	public void cargarListViewTickets(Persona p) {
		ObservableList<Tickets> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets(ticketsObservableList);
		listaTicketsSinResponder.setItems(ticketsObservableList);
		
		listaTicketsSinResponder.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Tickets ticket = listaTicketsSinResponder.getSelectionModel().getSelectedItem();
				enviarVentana(ticket);
			}

		});

	}

}
