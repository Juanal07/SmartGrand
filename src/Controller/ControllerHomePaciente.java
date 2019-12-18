package Controller;

import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import Model.Medico;
import Model.Persona;
import Model.Tickets;
import Controller.GsonGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerHomePaciente implements Initializable {
	@FXML
	private JFXListView<String> lvTicketsPaciente = new JFXListView<String>();
	@FXML
	private JFXButton fxmBtnEnviarTicket = new JFXButton();
	@FXML
	private JFXButton btnCerrarSesion = new JFXButton();
	@FXML
	private JFXTextArea jfxTaPaciente = new JFXTextArea();
	@FXML
	private Label lbError = new Label();
	private Label lbOculto = new Label();
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
				String ticket = "Paciente: " + tickets.getTextoPaciente() + "\t -> \t" + "Medico: "
						+ tickets.getTextoClinico();
				ticketsObservableList2.add(ticket);
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
		LoginControler loginControler = new LoginControler();
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
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}