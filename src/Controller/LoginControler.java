package Controller;

import java.awt.List;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Persona;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginControler {
	@FXML
	public ImageView imgLogo;
	@FXML
	public TextField jfxtUsuario = new TextField();
	@FXML
	public PasswordField jfxtPassword = new PasswordField();
	@FXML
	public Button btnIniciarSesion = new Button();
	@FXML
	public Button btnRegistrarse = new Button();
	@FXML
	public Label labelPregunta;
	@FXML
	public Label lbError = new Label();

	@FXML
	public void iniciarSesion(ActionEvent actionEvent) {
		String usuario = jfxtUsuario.getText();
		String contrasena = jfxtPassword.getText();
		System.out.println("Usuario: " + usuario + " -> Contraseña: " + contrasena);
		GsonPersona gsonPersonas = new GsonPersona();
		java.util.List<Persona> lista = GsonPersona.desserializarJsonAArray();
		for (Persona p : lista) {
			System.out.println("usuario json: " + p.getUsuario() + " -> password: " + p.getPassword());
			if (usuario.equals(p.getUsuario()) && contrasena.equals(p.getPassword())) {
				System.out.println("si esta el usuario");
				String tUsu = p.getTipoUsuario();
				// esta line es para cerrar la ventana anterior
				Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
				stage.close();
				switch (tUsu) {
				case "paciente":
					// creamos la ventana
					String vistaPaciente = "/View/VistaPaciente1.fxml";
					String tituloVista = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					VistaPacientePrincipalController registroContinuoControler = new VistaPacientePrincipalController();
					crearVentana(vistaPaciente, tituloVista, registroContinuoControler);
					break;
				case "medico":
					// creamos la ventana
					String vistaMedico = "/View/VistaMedico1.fxml";
					String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					VistaMedicoPrincipalController vistaMedicoPrincipalController = new VistaMedicoPrincipalController();
					crearVentana(vistaMedico, tituloVista2, vistaMedicoPrincipalController);
					break;
				case "cuidador":
					// creamos la ventana
					String vistaCuidador = "/View/VistaCuidador1.fxml";
					String tituloVista3 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					VistaCuidadorPrincipalController vistaCuidadorPrincipalController = new VistaCuidadorPrincipalController();
					crearVentana(vistaCuidador, tituloVista3, vistaCuidadorPrincipalController);
					break;

				default:
					break;
				}
				break;
			} else {
				System.out.println("Error: Usuario o contraseña INCORRECTO.");
				lbError.setText("Error: Usuario o contraseña INCORRECTO.");
				//JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@FXML
	public void registroContinuo(ActionEvent actionEvent) {
		// esta line es para cerrar la ventana anterior
		Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
		stage.close();
		// creamos la ventana
		String vistaRegContinuo = "/View/RegistroContinuo.fxml";
		String tituloVista = "RegistroContinuo";
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
			sendStage.setScene(scene);
			sendStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void validation(String cadena) {
		if (cadena.trim().equals("")) {
			System.out.println("No existe cadena");
		} else {
			System.out.println("Si existe cadena");
		}
	}
}
