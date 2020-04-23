package controller;
import java.awt.List;
import java.io.IOException;

import DataBase.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
		
		
	}
	@FXML
	public void usuarioEliminado(ActionEvent actionEvent) throws IOException {
		Conexion conexion3 = new Conexion();
		conexion3.eliminarPersona(personaBox.getValue());
		System.out.println("se elimino con exito");
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
}
