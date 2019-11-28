package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import Controller.GsonGeneral;
import Model.Persona;
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

public class CuidadorRegistroControler {
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
	public TextField tfUsuario = new TextField(), tfNombre = new TextField(), tfApellido = new TextField(), tfDni = new TextField();
	@FXML
	public PasswordField tfPassword = new PasswordField();

	@FXML
	public void pacienteRegistrado(ActionEvent actionEvent) throws IOException {
		String usuario = tfUsuario.getText();
		String password = GsonGeneral.getMd5(tfPassword.getText());
		String nombre = tfNombre.getText();
		String apellido = tfApellido.getText();
		String dni = tfDni.getText();
		String tipoUsuario = "cuidador";

		boolean valido = validation(usuario, password, nombre, apellido, tipoUsuario, dni);

		if(usuario != "" && password != "" && nombre != "" && apellido != "" && dni != "" && valido) {

			Persona nuevo = new Persona (usuario, password, nombre, apellido, tipoUsuario, dni); //Creamos objeto persona con los datos introducidos
			List<Persona> lista = GsonGeneral.desserializarJsonAArray(); //Creamos lista de personas con la info del json		
			lista.add(nuevo); //añadimos el nuevo usuario a la lista
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create(); //Pasamos la lista a formato json
			String representacionBonita = prettyGson.toJson(lista);
			String ruta = "usuarios.json";
			GsonGeneral.EscribirJson(representacionBonita, ruta);		

			Stage stage = (Stage) btnRegistrarse.getScene().getWindow(); // cerramos ventana
			stage.close();		

			String vistaRegPac = "/View/Login.fxml"; // creamos la nueva
			String tituloVista = "Login";
			LoginControler loginControler = new LoginControler();

			crearVentana(vistaRegPac, tituloVista, loginControler);
			//label indicando que se ha registrado con exito. en la ventana de iniciar sesion
			System.out.println("Cuidador registrado con exito");
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
		RegistroContinuoControler registroContinuoControler = new RegistroContinuoControler();
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

	public boolean esSoloLetras(String cadena) {
		// cogemos la cadena y la comparamos con su valor ASCII
		for (int i = 0; i < cadena.length(); i++) {
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int) caracter;
			System.out.println("Letra: " + caracter + " -> Valor ascii: " + valorASCII);
			if (valorASCII != 209 && (valorASCII < 65 || valorASCII > 90) && valorASCII != 193 && valorASCII != 201
					&& valorASCII != 205 && valorASCII != 211 && valorASCII != 218) {
				System.out.println("ERROR: se ha encontrado un caracter que no es letra.");
				return false; // Se ha encontrado un caracter que no es letra
			}
		}
		return true;
	}
	
	public boolean validation(String usuario, String password, String nombre, String apellido, String tipoUsuario,
			String dni) {
		boolean valido = true;
		if (usuario.matches("^[a-zA-Z0-9._-]{3,}$")) {
			lbErrorUsuario.setText("");
		} else {
			lbErrorUsuario.setText("Error! Nombre de usuario incorrecto.");
			valido = false;
		}
		if (nombre.matches("^[a-zA-Z]{2,}$")) {
			lbErrorNombre.setText("");
			//nombre = tfNombre.getText().intern();
		} else {
			lbErrorNombre.setText("Error! Nombre incorrecto.");
			valido = false;
		}

		if (apellido.matches("^[a-zA-Z]{2,}$")) {
			lbErrorApellido.setText("");
			//apellido = tfApellido.getText().intern();
		} else {
			lbErrorApellido.setText("Error! Apellido incorrecto.");
			valido = false;
		}
		
		if (password.matches("^[a-zA-Z0-9._-]{3,}$")) {
			lbErrorPassword.setText("");
			//apellido = tfApellido.getText().intern();
		} else {
			lbErrorPassword.setText("Error! Contraseña incorrecto.");
			valido = false;
		}

		if (dni.matches("^[a-zA-Z]{2,}$")) { //^(([A-Z]\\d{8})|(\\d{8}[A-Z]))$
			lbErrorDni.setText("");
			//apellido = tfDni.getText().intern();
		} else {
			lbErrorDni.setText("Error! DNI incorrecto.");
			valido = false;
		}
		return valido;
	}

}
