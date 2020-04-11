package controller;

import java.util.Calendar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import DataBase.Conexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.PersonaNew;
import model.TicketsNew;

public class ControllerResponderTicketMedico {
	@FXML
	private JFXTextArea txAreaMedico = new JFXTextArea();
	@FXML
	private JFXButton btnEnviar = new JFXButton();
	@FXML
	private JFXButton btnAtras = new JFXButton();
	@FXML
	private JFXScrollPane jfxScrollPane = new JFXScrollPane();
	@FXML
	private Label lbTextoPaciente = new Label();
	@FXML
	private Label lbFechaPaciente = new Label();
	@FXML
	private Label lbFechaMedico = new Label();
	@FXML
	private Label lbError = new Label();
	@FXML
	private Label lbIdMedico = new Label();// oculto
	@FXML
	private Label lbIdPaciente = new Label();// oculto
	@FXML
	private Label lbIdTicket = new Label();// oculto

	@FXML
	public void ventanaAtras() {
		Conexion conexion = new Conexion();
		PersonaNew p = new PersonaNew();
		p = conexion.consultaPersona("id_per", Integer.parseInt(lbIdMedico.getText()));
		Stage stage = (Stage) btnAtras.getScene().getWindow();
		stage.close();
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControllerHomeMedico controlerMedicoHome = new ControllerHomeMedico();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
			controlerMedicoHome.writeText(p);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista2);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setMaximized(true);
			stage2.setScene(new Scene(root1));
			stage2.show();
			controlerMedicoHome.cargarListViewPacientes(p);
			controlerMedicoHome.cargarListViewTickets(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarRespuesta() {
		if (txAreaMedico.getText().equals("")) {
			lbError.setText("Error: No puede enviar un Ticket vacio.");
		} else {
			String txtMedico = txAreaMedico.getText();
			Conexion conexion = new Conexion();
			conexion.respuestTicket(Integer.parseInt(lbIdTicket.getText()), lbFechaMedico.getText(), txtMedico);
		}
	}

	public void vaciarLb() {
		lbError.setText("");
		txAreaMedico.setText("");
	}

	public void ventanaHomeMedico() {
		Stage stage = (Stage) lbTextoPaciente.getScene().getWindow();
		stage.close();
		Conexion conexion = new Conexion();
		PersonaNew p = new PersonaNew();
		p = conexion.consultaPersona("id_per", Integer.parseInt(lbIdMedico.getText()));
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControllerHomeMedico controlerMedicoHome = new ControllerHomeMedico();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
			controlerMedicoHome.writeText(p);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista2);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.setMaximized(true);
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root1));
			stage2.show();
			controlerMedicoHome.cargarListViewPacientes(p);
			controlerMedicoHome.cargarListViewTickets(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ticket seleccionado y persona medico
	public void writeText(TicketsNew ticket, PersonaNew persona) {
		// convierto la fecha en string para meterla en el label
		lbFechaPaciente.setText(ticket.getFecha_Paciente().toString());
		java.sql.Date fecha_medico = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		lbFechaMedico.setText(fecha_medico.toString());
		lbTextoPaciente.setWrapText(true);
		lbTextoPaciente.setText(ticket.getTexto_Paciente());
		jfxScrollPane.setContent(lbTextoPaciente);
		lbIdPaciente.setText(Integer.toString(ticket.getId_paciente()));
		lbIdMedico.setText(Integer.toString(persona.getId_per()));
		lbIdTicket.setText(Integer.toString(ticket.getId_tic()));
	}
}
