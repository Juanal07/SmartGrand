package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PacienteRegistroController {
	@FXML
	private JFXButton btnAtras, btnRegistrarse;
	@FXML
	public JFXTextField tfUsuario = new JFXTextField(), tfPassword = new JFXTextField(),
			tfNombre = new JFXTextField(), tfApellido = new JFXTextField();

	@FXML
	public void pacienteRegistrado(ActionEvent actionEvent) {
		System.out.println("Paciente registrado...");
		String usuario = tfUsuario.getText();
		String password = tfPassword.getText();
		String nombre = tfNombre.getText();
		String apellido = tfApellido.getText();
		System.out.println("Usuario: " + usuario + " -> Password: " + password + " -> Nombre: " + nombre + " -> Apellido: " + apellido);
		validation(usuario);
		validation(password);
		validation(nombre);
		validation(apellido);
		// cerramos ventana
		Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegPac = "/View/Login.fxml";
		String tituloVista = "Login";
		LoginControler loginControler = new LoginControler();
		crearVentana(vistaRegPac, tituloVista, loginControler);
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

	public void verificarCampoVacio(String cadena) {
		if (cadena.equals("")) {
			System.out.println("campo vacion");
		} else {
			System.out.println("campo correcto");
		}
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

	public void validation(String cadena) {
		if (cadena.trim().equals("")) {
			System.out.println("No existe cadena");
		} else {
			System.out.println("Si existe cadena");
		}
	}

}
