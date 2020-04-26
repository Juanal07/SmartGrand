package controller;

import com.jfoenix.controls.JFXButton;

import DataBase.Conexion;
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

public class ControllerMedicoDatosPaciente {
	@FXML
	private Label lbNombre = new Label();
	@FXML
	private Label lbApeliido = new Label();
	@FXML
	private Label lbUsuario = new Label();
	@FXML
	private Label lbDni = new Label();
	@FXML
	private JFXButton btnAtras = new JFXButton();
	@FXML
	private JFXButton btnSensor1 = new JFXButton();
	@FXML
	private JFXButton btnSensor2 = new JFXButton();
	@FXML
	private JFXButton btnSensor3 = new JFXButton();
	private Label lbOcultoIdMedico = new Label();
	private Label id_paciente = new Label();

	public void writeText(PacienteNew paciente, String id_med) {
		id_paciente.setText(Integer.toString(paciente.getId_pac()));
		lbOcultoIdMedico.setText(id_med);
		Conexion conexion = new Conexion();// id per
		// datos del paciente
		PersonaNew p = new PersonaNew();
		p = conexion.consultaPersona("id_per", paciente.getId_pac());
		lbNombre.setText("Nombre: " + p.getNombre());
		lbApeliido.setText("Apellidos: " + p.getApellido());
		lbUsuario.setText("Usuario: " + p.getUsuario());
		lbDni.setText("Dni: " + p.getDni());
		// agregar localidad, fecha de nacimiento y num SegSocial
	}

	public void enviarSensor3() {
		String dniPaciente = lbDni.getText();
		try {
			ControllerSensor3Caidas controlBarChart = new ControllerSensor3Caidas();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor3caidas.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor Caidas");
			Scene scene = new Scene(page);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.show();
			controlBarChart.escibirDniPaciente(dniPaciente);
			controlBarChart.cargarGrafica();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarSensor2() {
		String dniPaciente = lbDni.getText();
		try {
			ControllerSensor2Puerta controlBarChart = new ControllerSensor2Puerta();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor2puerta.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor Puerta");
			Scene scene = new Scene(page);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.show();
			controlBarChart.escibirDniPaciente(dniPaciente);
			controlBarChart.cargarGrafica();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarSensor1() {
		try {
			ControllerSensor1 controlBarChart = new ControllerSensor1();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor1Presion.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor Presion");
			Scene scene = new Scene(page);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.show();
			controlBarChart.escibirDniPaciente(id_paciente.getText());
			controlBarChart.cargarGrafica();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ventanaAtras() {
		Conexion conexion = new Conexion();
		PersonaNew p = new PersonaNew();
		p = conexion.consultaPersona("id_per", Integer.parseInt(lbOcultoIdMedico.getText()));
		Stage stage = (Stage) lbNombre.getScene().getWindow();
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
			stage2.setScene(new Scene(root1));
			stage2.show();
			controlerMedicoHome.cargarListViewPacientes(p);
			controlerMedicoHome.cargarListViewTickets(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
