package Controller;

import com.jfoenix.controls.JFXButton;

import Model.Persona;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MedicoDatosPaciente {
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
	private Label lbOcultoPacienteMedico = new Label();
	private Label lbOcultoYo = new Label();

	public void writeText(Persona persona, Persona yo) {
		lbOcultoYo.setText(yo.toString());
		lbOcultoPacienteMedico.setText(persona.toString());
		lbNombre.setText("Nombre: " + persona.getNombre());
		lbApeliido.setText("Apellidos: " + persona.getApellido());
		lbUsuario.setText("Usuario: " + persona.getUsuario());
		lbDni.setText("Dni: " + persona.getDni());
	}
	
	public void enviarSensor1() {
		String dniPaciente = lbDni.getText();
		try {
			ControllerSensor1Presion controlBarChart = new ControllerSensor1Presion();
			FXMLLoader root2 =  new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/sensor1Presion.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			sendStage.setTitle("Sensor Presion");
			Scene scene = new Scene(page);
			sendStage.setScene(scene);
			sendStage.show();
			controlBarChart.escibirDniPaciente(dniPaciente);
			controlBarChart.cargarGrafica();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void ventanaAtras() {
		String usu, pass, nom, apell, tpU, dni;
		String[] per = lbOcultoYo.getText().split("\t");
		usu = per[0];
		pass = per[1];
		nom = per[2];
		apell = per[3];
		tpU = per[4];
		dni = per[5];
		Persona p = new Persona(usu, pass, nom, apell, tpU, dni);
		Stage stage = (Stage) lbNombre.getScene().getWindow();
		stage.close();
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControlerMedicoHome controlerMedicoHome = new ControlerMedicoHome();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
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
