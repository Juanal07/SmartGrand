package controller;

import com.jfoenix.controls.JFXButton;

import dataBase.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PacienteNew;
import model.PersonaNew;
import model.Sensor1New;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMedicoDatosPaciente implements Initializable {
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

	@FXML
	LineChart<String, Number> chart1;

	@FXML
	BarChart<String, Number> chart2;

	@FXML
	AreaChart<String, Number> chart3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Conexion conexion = new Conexion();
		ArrayList<Sensor1New> data1 = conexion.cargarSensor(1,"temperatura");

		XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		series3.setName("Temperatura");
		for (int i = 0; i < data1.size(); i++) {
			String fecha = data1.get(i).getFecha();
			int valor = data1.get(i).getDato();
			series3.getData().add(new XYChart.Data<>(fecha, valor));
		}
		chart1.getData().add(series3);

		Conexion conexion2 = new Conexion();
		ArrayList<Sensor1New> data4 = conexion2.cargarSensor(1,"puerta");

		XYChart.Series<String, Number> series4 = new XYChart.Series<>();
		series4.setName("Puerta");
		for (int j = 0; j < data4.size(); j++) {
			String fecha = data4.get(j).getFecha();
			int valor = data4.get(j).getDato();
			series4.getData().add(new XYChart.Data<>(fecha, valor));
		}
		chart2.getData().add(series4);


		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Juan");
		series1.getData().add(new XYChart.Data("Monday", 3));
		series1.getData().add(new XYChart.Data("Tuesday", 4));
		series1.getData().add(new XYChart.Data("Wednesday", 3));
		series1.getData().add(new XYChart.Data("Thursday", 5));
		series1.getData().add(new XYChart.Data("Friday", 4));
		series1.getData().add(new XYChart.Data("Saturday", 10));
		series1.getData().add(new XYChart.Data("Sunday", 12));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Media");
		series2.getData().add(new XYChart.Data("Monday", 1));
		series2.getData().add(new XYChart.Data("Tuesday", 3));
		series2.getData().add(new XYChart.Data("Wednesday", 4));

		series2.getData().add(new XYChart.Data("Thursday", 3));
		series2.getData().add(new XYChart.Data("Friday", 3));
		series2.getData().add(new XYChart.Data("Saturday", 5));
		series2.getData().add(new XYChart.Data("Sunday", 4));

		chart3.getData().addAll(series1,series2);

	}

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
		try {
			ControllerSensor3 controlBarChart = new ControllerSensor3();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor3.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor 3");
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

	public void enviarSensor2() {
		try {
			ControllerSensor2 controlBarChart = new ControllerSensor2();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor2.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor 2");
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

	public void enviarSensor1() {
		try {
			ControllerSensor1 controlBarChart = new ControllerSensor1();
			FXMLLoader root2 = new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/Sensor1.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor 1");
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
