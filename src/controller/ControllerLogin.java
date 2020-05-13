package controller;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import dataBase.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PersonaNew;

public class ControllerLogin implements Initializable {
	@FXML
	public ImageView imgLogo;
	@FXML
	public TextField jfxtUsuario = new TextField();
	@FXML
	public PasswordField jfxtPassword  = new PasswordField();
	@FXML
	public Button btnIniciarSesion = new Button();
	@FXML
	public Button btnRegistrarse = new Button();
	@FXML
	public Label labelPregunta;
	@FXML
	public Label lbError = new Label();
	@FXML
	public WebView web;
	@FXML
	public WebView web1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		web.getEngine().load("https://twitter.com/elonmusk");
		web1.getEngine().load("https://www.instagram.com/ueuropea/");
	}

	@FXML
	public void iniciarSesion() {
		try {
			String usuario = jfxtUsuario.getText();
			String psw = GsonGeneral.getMd5(jfxtPassword.getText());
			Conexion conexion = new Conexion();
			PersonaNew persona = conexion.consultaPersonaUsuario(usuario);

			if (!(usuario.equals("")) && (persona.getpsw().equals(psw))) {
				System.out.println("psw correcto");
				if (usuario.equals("admin")) {
					adminHome();
				}
				String tUsu = persona.getTipo();

				System.out.println(persona.getTipo());

				Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
				stage.close();

				switch (tUsu) {
				case "paciente":
					pacienteHome(persona);
					break;
				case "medico":
					medicoHome(persona);
					break;
				case "cuidador":
					cuidadorHome(persona);
					break;
				default:
					break;
				}

			} else {
				System.out.println("passoword incorrecto");
				lbError.setText("Error: Usuario o psw INCORRECTO.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void linkPulsado(ActionEvent event) {
		try {

			Desktop.getDesktop().browse(new URI("https://twitter.com/SmartGrandOffi1"));

		} catch (Exception e) {
			e.printStackTrace();
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

	public void pacienteHome(PersonaNew p) {
		try {
			String vistaPaciente = "/View/HomePaciente.fxml";
			String tituloVista = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ControllerHomePaciente controllerHomePaciente = new ControllerHomePaciente();
			loader.setController(controllerHomePaciente);
			Parent root2 = loader.load();
			controllerHomePaciente.writeText(p);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root2));
			stage2.show();
			controllerHomePaciente.cargarListaTickets(p);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void medicoHome(PersonaNew persona) {
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + persona.getNombre() + " " + persona.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControllerHomeMedico controlerMedicoHome = new ControllerHomeMedico();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
			controlerMedicoHome.writeText(persona);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista2);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root1));
			stage2.show();
			controlerMedicoHome.cargarListViewPacientes(persona);
			controlerMedicoHome.cargarListViewTickets(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cuidadorHome(PersonaNew persona) {
		try {
			String vistaCuidador = "/View/VistaCuidador1.fxml";
			String tituloVista3 = "Bienvenido: " + persona.getNombre() + " " + persona.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaCuidador));
			ControllerHomeCuidador vistaCuidadorPrincipalController = new ControllerHomeCuidador();
			loader.setController(vistaCuidadorPrincipalController);
			Parent root2 = loader.load();
			vistaCuidadorPrincipalController.writeText(persona);
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista3);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la
																									// vista
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root2));
			stage2.show();
			vistaCuidadorPrincipalController.cargarTableview(persona);
		} catch (Exception e) {
			e.printStackTrace();
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
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Scene scene = new Scene(page);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
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
