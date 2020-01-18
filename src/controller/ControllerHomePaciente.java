package controller;

import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GsonGeneral;
import model.Medico;
import model.Persona;
import model.Tickets;

public class ControllerHomePaciente  {
	@FXML
	private JFXListView<Tickets> lvTicketsPaciente = new JFXListView<Tickets>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXButton btnCerrarSesion = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();
	@FXML
	private Label lbError = new Label();
	private Label lbOculto = new Label();
	private Label lbOcultoObjetoYo = new Label();
	@FXML
	private ObservableList<String> ticketsObservableList;
	@FXML
	private JFXScrollPane scroll = new JFXScrollPane();
	
	public void vaciarTXError() {
		lbError.setText("");
	}
	public void enviarMSM() {
		String textoPaciente = jfxTaPaciente.getText();
		String dniPaciente = lbOculto.getText();
		String dniMedico = "";
		if (!textoPaciente.equals("")) {
			List<Medico> listaMedicoRelacion = GsonGeneral.desserializarJsonAArrayMedico();
			for (Medico medico : listaMedicoRelacion) {
				ArrayList<String> idPacientes = medico.getDniPacientes();
				int sizeArray = idPacientes.size();
				int i = 0;
				while (i < sizeArray) {
					if (dniPaciente.equals(idPacientes.get(i))) {
						dniMedico = medico.getIdMedico();
						i = i + sizeArray;
					}
					i++;
				}
			}
			Tickets nuevo = new Tickets(dniPaciente, dniMedico, textoPaciente, "");
			EnviarTicket(nuevo);
			jfxTaPaciente.setText("");
			lbError.setWrapText(true);
			lbError.setText("Ticket enviado con exito.");
		} else {
			// envuelve el label
			lbError.setWrapText(true);
			lbError.setText("ERROR: No puede enviar un tiquet vacio.");
		}

	}

	public void EnviarTicket(Tickets ticket) {
		String ruta = "jsonTickets.json";
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		tiquets.add(ticket);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(tiquets);
		GsonGeneral.EscribirJson(representacionBonita, ruta);
	}

	public void writeText(Persona p) {
		lbOculto.setText(p.getDni());
		lbOcultoObjetoYo.setText(p.toString());
	}

	
	public void cargarListaTickets(Persona p) {
		ObservableList<Tickets> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets(ticketsObservableList, p);
		lvTicketsPaciente.setItems(ticketsObservableList);
		lvTicketsPaciente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tickets>() {
		
			@Override
			public void changed(ObservableValue<? extends Tickets> observable, Tickets oldValue, Tickets newValue) {			
				Tickets tickets = lvTicketsPaciente.getSelectionModel().getSelectedItem();
				String[] prPaciente = lbOcultoObjetoYo.getText().split("\t");
				String usu, pass, nom, apell, tpU, dni;
				usu = prPaciente[0];
				pass = prPaciente[1];
				nom = prPaciente[2];
				apell = prPaciente[3];
				tpU = prPaciente[4];
				dni = prPaciente[5];
				Persona persona = new Persona(usu, pass, nom, apell, tpU, dni);
				ventanaDatosTicket(persona, tickets);
			}
		});
	}
	
	private void ventanaDatosTicket(Persona persona, Tickets tickets) {
		String vistaDatosPaciente = "/View/TicketsCompletoPaciente.fxml";
		String tituloVista = "Datos Ticket.";
		Stage stage = (Stage) jfxTaPaciente.getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaDatosPaciente));
			ControllerVistaTicketPaciente vistaTicketPaciente = new ControllerVistaTicketPaciente();
			loader.setController(vistaTicketPaciente);
			Parent root = loader.load();
			vistaTicketPaciente.writeText(persona, tickets);
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

	private void leerTickets(ObservableList<Tickets> ticketsObservableList2, Persona p) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (tickets.getIdPaciente().equals(p.getDni())) {
				ticketsObservableList2.add(tickets);
			}
		}
	}

	public void cerrarSesion() {
		// cerramos ventana
		Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/Login.fxml";
		String tituloVista = "Login";
		ControllerLogin loginControler = new ControllerLogin();
		crearVentana(vistaRegContinuo, tituloVista, loginControler);
	}

	public void crearVentana(String vista, String titulo, Object object) {
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
}