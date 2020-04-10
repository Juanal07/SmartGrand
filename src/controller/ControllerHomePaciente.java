package controller;

import java.util.Calendar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import DataBase.Conexion;
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
import model.PersonaNew;
import model.TicketsNew;

public class ControllerHomePaciente {
	@FXML
	private JFXListView<TicketsNew> lvTicketsPaciente = new JFXListView<TicketsNew>();
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
	@FXML
	private Label labelPaciente = new Label();

	public void vaciarTXError() {
		lbError.setText("");
	}

	public void enviarMSM() {
		Conexion conexion = new Conexion();

		String textoPaciente = jfxTaPaciente.getText();
		String dniPaciente = lbOculto.getText();
		// averiguar el dni del medico
		// coger el id de paciente y despues hacer una consulta en medicos para buscar a
		// su medic0
		int idPaciente = conexion.consultaPersona(dniPaciente).getId_per();
		int idMedico = conexion.consultaMedico(idPaciente).getId_med();
		if (!textoPaciente.equals("")) {
			java.sql.Date fecha_paciente = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			// TicketsNew nuevo = new TicketsNew(idPaciente, idMedico, textoPaciente, "",
			// fecha_paciente, null);
			conexion.istTicket(conexion, textoPaciente, fecha_paciente, "", null, idMedico, idPaciente);
			jfxTaPaciente.setText("");
			lbError.setWrapText(true);
			lbError.setText("Ticket enviado con exito.");
		} else {
			// envuelve el label
			lbError.setWrapText(true);
			lbError.setText("ERROR: No puede enviar un tiquet vacio.");
		}

	}

	public void writeText(PersonaNew p) {
		lbOculto.setText(p.getDni());
		lbOcultoObjetoYo.setText(p.toString());
		labelPaciente.setText(
				"Paciente: Bienvenido/a " + p.getNombre() + " \naqui puedes ver tu lista de tickets respondidos");
	}

	public void cargarListaTickets(PersonaNew p) {
		Conexion conexion = new Conexion();
		ObservableList<TicketsNew> ticketsObservableList = FXCollections.observableArrayList();
		conexion.leerTickets(ticketsObservableList, p);
		lvTicketsPaciente.setItems(ticketsObservableList);
		lvTicketsPaciente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TicketsNew>() {

			@Override
			public void changed(ObservableValue<? extends TicketsNew> observable, TicketsNew oldValue,
					TicketsNew newValue) {
				TicketsNew tickets = lvTicketsPaciente.getSelectionModel().getSelectedItem();
				ventanaDatosTicket(conexion.consultaPersona(p.getDni()), tickets);
			}
		});
	}

	private void ventanaDatosTicket(PersonaNew persona, TicketsNew tickets) {
		String vistaDatosPaciente = "/View/TicketsCompletoPaciente.fxml";
		String tituloVista = "Datos Ticket.";
//		Stage stage = (Stage) jfxTaPaciente.getScene().getWindow();
//		stage.close();
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