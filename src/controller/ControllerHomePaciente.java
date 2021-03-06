package controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import dataBase.Conexion;
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
import model.PacienteNew;
import model.PersonaNew;
import model.TicketsNew;

public class ControllerHomePaciente {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
	private Label lbOcultoIdPer = new Label();
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
		String IdPaciente = lbOcultoIdPer.getText();
		// averiguar el dni del medico
		// coger el id de paciente y despues hacer una consulta en medicos para buscar a
		// su medic0
		int idPaciente = conexion.consultaPersona("id_per", IdPaciente).getId_per();
		PacienteNew pa = new PacienteNew();
		pa = conexion.consultaPaciente("id_pac",idPaciente);
		if (!textoPaciente.equals("")) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			//java.sql.Date fecha_paciente = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			//System.out.println("yo paciente id: " + idPaciente +" id medico: " + pa.getId_medico() + "	fecha: " + sdf.format(timestamp));
		
			conexion.istTicketPaciente(conexion, textoPaciente, sdf.format(timestamp).toString(), pa.getId_medico(), idPaciente);
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
		lbOcultoIdPer.setText(Integer.toString(p.getId_per()));
		lbOcultoObjetoYo.setText(p.toString());
		labelPaciente.setText(
				"Paciente: Bienvenido/a " + p.getNombre() + " \naqui puedes ver tu lista de tickets respondidos");
	}
	// aqui esta el error
	public void cargarListaTickets(PersonaNew p) {
		Conexion conexion = new Conexion();
		ObservableList<TicketsNew> ticketsObservableList = FXCollections.observableArrayList();
		conexion.leerTicketsPaciente(ticketsObservableList,"id_paciente", p.getId_per());
		//ticketsObservableList = filtro(ticketsObservableList);
		lvTicketsPaciente.setItems(ticketsObservableList);
		lvTicketsPaciente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TicketsNew>() {

			@Override
			public void changed(ObservableValue<? extends TicketsNew> observable, TicketsNew oldValue,
					TicketsNew newValue) {
				TicketsNew tickets = lvTicketsPaciente.getSelectionModel().getSelectedItem();
				ventanaDatosTicket(p, tickets);
			}
		});
	}
	// revisar
	private ObservableList<TicketsNew> filtro(ObservableList<TicketsNew> ticketsObservableList) {
		for (int i = 0; i < ticketsObservableList.size()-1; i++) {
			System.out.println("texto paciente: " + ticketsObservableList.get(i));
			if (ticketsObservableList.get(i).getTexto_Medico().equals("")) {
				ticketsObservableList.remove(i);
			}
		}
		return ticketsObservableList;
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
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root,1280,720));
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
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}