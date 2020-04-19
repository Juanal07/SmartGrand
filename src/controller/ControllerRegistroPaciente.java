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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Cuidador;
import model.Medico;
import model.Persona;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ControllerRegistroPaciente {
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
	private Label lbErrorLocalidad;
	@FXML
	private Label lbErrorSegSocial;
	@FXML
	private Label lbErrorNac;
	@FXML
	public TextField tfUsuario = new JFXTextField(), tfNombre = new JFXTextField(), tfApellido = new JFXTextField(),
			tfDni = new JFXTextField(), tfNumSegSocial = new TextField(), tfLocalidad = new TextField();
	@FXML
	public PasswordField tfPassword = new PasswordField();


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
		String localidad = tfLocalidad.getText();
		String numSocial = tfNumSegSocial.getText();
		String tipo = "paciente";

		// hacer lo de validar faltan 3 campos
		boolean valido = validation(usuario, password, nombre, apellido, dni, fechaNacimiento, localidad, numSocial);

		if (usuario != "" && password != "" && nombre != "" && apellido != "" && dni != "" && valido) {

			Conexion conexion = new Conexion();
			conexion.istPersona(conexion, nombre, apellido, usuario, passwordCifrada, dni, fechaNacimiento.toString(),
					tipo);
			conexion.istPaciente(conexion, conexion.consultaPersona("dni", dni).getId_per(), localidad,
					Integer.parseInt(numSocial), 999999, 99999);

			Stage stage = (Stage) btnRegistrarse.getScene().getWindow(); // cerramos ventana
			stage.close();
			String vistaRegPac = "/View/Login.fxml"; // creamos la nueva
			String tituloVista = "Login";
			ControllerLogin loginControler = new ControllerLogin();
			crearVentana(vistaRegPac, tituloVista, loginControler);
			// label indicando que se ha registrado con exito. en la ventana de iniciar
			// sesion
			System.out.println("Paciente registrado con exito");

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
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean validation(String usuario, String password, String nombre, String apellido, String dni,
			LocalDate fechaNac, String localidad, String numSegSocial) {
		boolean valido = true;

		if ((dni.matches("\\d{8}[A-HJ-NP-TV-Z]"))) {
			lbErrorDni.setText("");
			if (GsonGeneral.validarNIF(dni)) {
				lbErrorDni.setText("");
				if (!GsonGeneral.seRepiteDnis(dni)) {
					lbErrorDni.setText("");
				} else {
					System.out.println(GsonGeneral.seRepiteDnis(dni));
					lbErrorDni.setText("El DNI ya esta registrado");
					valido = false;
				}
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
			lbErrorPassword.setText("Tu password debe contener al menos 8 caracteres");
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

		if ((fechaNac != null)
				&& fechaNac.toString().matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
			lbErrorNac.setText("");
		} else {
			lbErrorNac.setText("Tu fecha de nacimiento no es valida");
			valido = false;
		}

		if (localidad.matches("^[a-zA-Z]{2,}$")) {
			lbErrorLocalidad.setText("");
		} else {
			lbErrorLocalidad.setText("Tu especialidad debe contener al menos 2 letras");
			valido = false;
		}

		if (numSegSocial.matches("^[a-zA-Z0-9._-]{8,}$")) {
			lbErrorSegSocial.setText("");
		} else {
			lbErrorSegSocial.setText("Tu numero de seguridad social debe contener al menos 8 caracteres");
			valido = false;
		}

		return valido;
	}
}
