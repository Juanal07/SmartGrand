package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerRegistroContinuo {
	@FXML
	public Button btnPaciente;
	@FXML
	public Button btnCuidador;
	@FXML
	public Button btnClinico;

	@FXML
	public void PacienteRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnPaciente.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/PacienteRegistro.fxml";
		String tituloVista = "Registro para usuarios";
		ControllerRegistroPaciente PacienteRegistroController = new ControllerRegistroPaciente();
		crearVentana(vistaRegContinuo, tituloVista, PacienteRegistroController);
	}

	@FXML
	public void atrasLogin(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnPaciente.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/Login.fxml";
		String tituloVista = "Login";
		ControllerLogin loginControler = new ControllerLogin();
		crearVentana(vistaRegContinuo, tituloVista, loginControler);
	}

	@FXML
	public void CuidadorRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnCuidador.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/CuidadorRegistro.fxml";
		String tituloVista = "Registro para cuidadores";
		ControllerRegistroCuidador cuidadorRegistroControler = new ControllerRegistroCuidador();
		crearVentana(vistaRegContinuo, tituloVista, cuidadorRegistroControler);
	}

	@FXML
	public void clinicoRegistro(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnClinico.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/ClinicoRegistro.fxml";
		String tituloVista = "Registro para clinicos";
		ControllerRegistroMedico clinicoRegistroControler = new ControllerRegistroMedico();
		crearVentana(vistaRegContinuo, tituloVista, clinicoRegistroControler);
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
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // a�ade icono a la vista	
			sendStage.getIcons().add(icon);
			sendStage.setMaximized(true);
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
