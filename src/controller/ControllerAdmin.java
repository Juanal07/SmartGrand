package controller;

import java.awt.List;
import java.io.IOException;

import DataBase.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PersonaNew;

public class ControllerAdmin {
	@FXML
	public Button btnAtras;

	@FXML
	public Button verificar;
	@FXML
	public Button eliminar;
	@FXML
	private ComboBox<String> medicosBox;

	ObservableList<String> medicosNoVerificados = FXCollections.observableArrayList();
	@FXML
	private ComboBox<String> personaBox;

	ObservableList<String> personas = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		Conexion conexion = new Conexion();
		java.util.List<String> lista = conexion.listaMedicosNoVerificados();

		for (String p : lista) {
			medicosNoVerificados.add(p);
		}
		medicosBox.setValue("Elige medico");
		medicosBox.setItems(medicosNoVerificados);

		Conexion conexion1 = new Conexion();
		java.util.List<String> lista2 = conexion1.listaUsuarios();

		for (String a : lista2) {
			personas.add(a);
		}
		personaBox.setValue("Elige usuario");
		personaBox.setItems(personas);

	}

	@FXML
	public void medicoVerificado(ActionEvent actionEvent) throws IOException {
		String string = medicosBox.getValue();
		String[] parts = string.split("-");
		String part1 = parts[0];
		System.out.println(part1);
		Conexion conexion = new Conexion();
		int a = Integer.parseInt(part1);
		conexion.verificarMedico(a);
		System.out.println(part1);
		System.out.println("Medico verificado");
		adminHome();
		Stage stage = (Stage) verificar.getScene().getWindow();
		stage.close();

	}

	@FXML
	public void usuarioEliminado(ActionEvent actionEvent) throws IOException {
		System.out.println(personaBox.getValue());
		String string = personaBox.getValue();
		Conexion conexion3 = new Conexion();
		conexion3.eliminarPersona(string);
		System.out.println("Se elimino con exito");
		adminHome();
		Stage stage = (Stage) eliminar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void atrasLogin(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnAtras.getScene().getWindow();
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
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // a�ade icono a la
																									// vista
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void adminHome() {
		try {
			String vistaCuidador = "/View/AdminHome.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaCuidador));
			ControllerAdmin vistaAdmin = new ControllerAdmin();
			loader.setController(vistaAdmin);
			Parent root2 = loader.load();
			Stage stage2 = new Stage();
			stage2.setTitle("Administrador");
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la
																									// vista
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root2));
			stage2.show();
//			vistaCuidadorPrincipalController.cargarTableview(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
