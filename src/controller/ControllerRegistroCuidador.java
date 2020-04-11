package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import DataBase.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ControllerRegistroCuidador {
	@FXML
	private Button btnAtras, btnRegistrarse;
	@FXML
	private Label lbErrorUsuario;
	@FXML
	private Label lbErrorPassword;
	@FXML
	private Label lbErrorNombre;
	@FXML
	private Label lbErrorApellido;
	@FXML
	private Label lbErrorDni;
	@FXML
	private TextField tfUsuario = new JFXTextField(), tfNombre = new JFXTextField(), tfApellido = new JFXTextField(),
			tfDni = new JFXTextField(), tfEspecialidad = new TextField();
	@FXML
	private PasswordField tfPassword = new PasswordField();
	@FXML
	private JFXDatePicker dpFechaNacimiento = new JFXDatePicker();

	@FXML
	public void pacienteRegistrado(ActionEvent actionEvent) throws IOException {
		
		String usuario = tfUsuario.getText();
		String password = tfPassword.getText();// password sin cifrar para hacer el validation
		String passwordCifrada = GsonGeneral.getMd5(tfPassword.getText());
		String nombre = tfNombre.getText();
		String apellido = tfApellido.getText();
		String dni = tfDni.getText();
		LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
		String especialidad = tfEspecialidad.getText();
		String tipo = "cuidador";
		
		System.out.println(GsonGeneral.seRepiteDnis(dni));
		
		boolean valido = validation(usuario, password, nombre, apellido, dni);

		if (usuario != "" && password != "" && nombre != "" && apellido != "" && dni != ""&& valido) {
			
			Conexion conexion = new Conexion();
			conexion.istPersona(conexion, nombre, apellido, usuario, passwordCifrada, dni, fechaNacimiento.toString(), tipo);
			conexion.istCuidador(conexion, conexion.consultaPersona(dni).getId_per(), especialidad, 99999);


			Stage stage = (Stage) btnRegistrarse.getScene().getWindow(); // cerramos ventana
			stage.close();

			String vistaRegPac = "/View/Login.fxml"; // creamos la nueva
			String tituloVista = "Login";
			ControllerLogin loginControler = new ControllerLogin();

			crearVentana(vistaRegPac, tituloVista, loginControler);
			// label indicando que se ha registrado con exito. en la ventana de iniciar
			// sesion
			System.out.println("Cuidador registrado con exito");
		}else {
			System.out.println("no se pudo registrar");
		}
	}

	@FXML
	public void atrasRegContinuo(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnAtras.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/RegistroContinuo.fxml";
		String tituloVista = "Registro continuo";
		ControllerRegistroContinuo registroContinuoControler = new ControllerRegistroContinuo();
		crearVentana(vistaRegContinuo, tituloVista, registroContinuoControler);
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

	public boolean validation(String usuario, String password, String nombre, String apellido, String dni) {
		boolean valido = true;

		if ((dni.matches("\\d{8}[A-HJ-NP-TV-Z]"))) {
			lbErrorDni.setText("");
			if (!GsonGeneral.seRepiteDnis(dni)) {
				lbErrorDni.setText("");
			} else {
				lbErrorDni.setText("El DNI ya esta registrado");
				valido = false;
			}
			if (GsonGeneral.validarNIF(dni)) {
				lbErrorDni.setText("");
			} else {
				lbErrorDni.setText("El DNI no es real");
				valido = false;
			}
		} else {
			lbErrorDni.setText("El DNI debe llevar 8 numeros y una letra mayuscula");
			valido = false;
		}

		if (usuario.matches("^[a-zA-Z0-9._-]{3,}$")) {
			lbErrorUsuario.setText("");
			if (!GsonGeneral.seRepiteUsuario(usuario)) {
				lbErrorUsuario.setText("");
			} else {
				lbErrorUsuario.setText("El Usuario ya esta registrado");
				valido = false;
			}
		} else {
			lbErrorUsuario.setText("El usuario debe ser de al menos 3 caracteres");
			valido = false;
		}
		if (password.matches("^[a-zA-Z0-9._-]{8,}$")) {
			lbErrorPassword.setText("");
		} else {
			lbErrorPassword.setText("La contrase�a debe contener al menos 8 caracteres");
			valido = false;
		}
		if (nombre.matches("^[a-zA-Z]{2,}$")) {
			lbErrorNombre.setText("");
		} else {
			lbErrorNombre.setText("Tu nombre debe contener al menos 2 letras");
			valido = false;
		}

		if (apellido.matches("^[a-zA-Z]{2,}$")) {
			lbErrorApellido.setText("");
		} else {
			lbErrorApellido.setText("Tu apellido debe contener al menos 2 letras");
			valido = false;
		}

		return valido;
	}

}
