package controller;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Medico;
import model.Persona;
import model.Tickets;

public class ControllerHomeMedico {
	@FXML
	private JFXListView<Persona> listaPacientesMedico = new JFXListView<Persona>();
	@FXML
	private JFXListView<Tickets> listaTicketsSinResponder = new JFXListView<Tickets>();
	@FXML
	private JFXButton btnCerrarSesion = new JFXButton();
	private Label lbOculto = new Label();
	@FXML
	private Label labelMedico = new Label();
	
	@FXML
	public void cerrarSesion(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/Login.fxml";
		String tituloVista = "Login";
		ControllerLogin loginControler = new ControllerLogin();
		crearVentana(vistaRegContinuo, tituloVista, loginControler);
	}
	
	public void writeText(Persona p) {
		labelMedico.setText("Medico: Bienvenido/a " + p.getNombre());
		lbOculto.setText(p.getDni());
	}

	private void crearVentana(String vista, String titulo, Object object) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource(vista));
			loader.setController(object);
			AnchorPane page = (AnchorPane) loader.load();
			Stage sendStage = new Stage();
			sendStage.setTitle(titulo);
			Scene scene = new Scene(page);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setMaximized(true);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void leerPersonas(ObservableList<Persona> pacientesObservableList2, Persona p) {
		ArrayList<String> listaMisPacientes;
		listaMisPacientes = listaPersonas(p);// devuleve una lista con los ids de los pacientes
		List<Persona> lista = GsonGeneral.desserializarJsonAArray();// lista con las personas
		
		//***********************************
		boolean rompeBucle1 = false;
		boolean rompeBucle2 = false;
		int cont1 = 0;
		
		while(cont1 < lista.size() && !rompeBucle1) { // este while actua como for 
			// hacer lo que querais jajaja 
			int cont2 = 0;
			while(cont2 < listaMisPacientes.size() && !rompeBucle2) {
				if (lista.get(cont1).getDni().equals(listaMisPacientes.get(cont2))) {
					lista.get(cont1).setPassword("");// para que no apareescan las contraseņas en la lista
					pacientesObservableList2.add(lista.get(cont1));
					cont2 = listaMisPacientes.size();
				}
				cont2++;
			}
			cont1++;
		}
	
		// recorremos personas
//		int lenthArray = listaMisPacientes.size();
//		for (int i = 0; i < lista.size(); i++) {
//			int j = 0;
//			// recorremos mis pacientes
//			while (j < lenthArray) {
//				if (lista.get(i).getDni().equals(listaMisPacientes.get(j))) {
//					pacientesObservableList2.add(lista.get(i));
//					j = j + lenthArray;
//				}
//				j++;
//			}
//		}
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

	public void enviarVentana(Tickets ticket, Persona p) {
		String vistaPaciente = "/View/ResponderTicketMedico.fxml";
		String tituloVista = "responder tiquet.";
		String persona = lbOculto.getText();
		Stage stage = (Stage) listaTicketsSinResponder.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ControllerResponderTicketMedico responderTicketMedicoControler = new ControllerResponderTicketMedico();
			loader.setController(responderTicketMedicoControler);
			Parent root2 = loader.load();
			responderTicketMedicoControler.writeText(ticket, p);
			Stage stage2 = new Stage();
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setMaximized(true);
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root2));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void leerTickets2(ObservableList<Tickets> ticketsObservableList2, Persona p) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (tickets.getIdClinico().equals(p.getDni())) {
				if (tickets.getTextoClinico().equals("")) {
					ticketsObservableList2.add(tickets);
				}
		}}
	}

	public void cargarListViewPacientes(Persona p) {
		
		lbOculto.setText(p.toString());
		ObservableList<Persona> personasObservableList = FXCollections.observableArrayList();
		leerPersonas(personasObservableList, p);// encuentra los objetos de las personas y los mete en personasObservableList
		
		listaPacientesMedico.setItems(personasObservableList);

		listaPacientesMedico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Persona>() {

			@Override
			public void changed(ObservableValue<? extends Persona> observable, Persona oldValue, Persona newValue) {
				Persona persona = listaPacientesMedico.getSelectionModel().getSelectedItem();
				String[] prMedico = lbOculto.getText().split("\t");
				String usu, pass, nom, apell, tpU, dni;
				
				usu = prMedico[0];
				pass = prMedico[1];
				nom = prMedico[2];
				apell = prMedico[3];
				tpU = prMedico[4];
				dni = prMedico[5];
				Persona yo = new Persona(usu, pass, nom, apell, tpU, dni);
				ventanaDatosPaciente(persona, yo);
			}
		});
	}

	public void ventanaDatosPaciente(Persona persona, Persona yo) {
		String vistaDatosPaciente = "/View/MedicoPacienteLectura.fxml";
		String tituloVista = "Datos Paciente.";
		Stage stage = (Stage) listaTicketsSinResponder.getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaDatosPaciente));
			ControllerMedicoDatosPaciente medicoDatosPaciente = new ControllerMedicoDatosPaciente();
			loader.setController(medicoDatosPaciente);
			Parent root = loader.load();
			medicoDatosPaciente.writeText(persona, yo);
			Stage stage2 = new Stage();
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setMaximized(true);
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarListViewTickets(Persona p) {
		ObservableList<Tickets> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets2(ticketsObservableList, p);
		listaTicketsSinResponder.setItems(ticketsObservableList);

		listaTicketsSinResponder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tickets>() {

			@Override
			public void changed(ObservableValue<? extends Tickets> observable, Tickets oldValue, Tickets newValue) {
				Tickets ticket = listaTicketsSinResponder.getSelectionModel().getSelectedItem();
				enviarVentana(ticket, p);

			}
		});
	}
}
