package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GsonGeneral;
import model.Persona;

public class ControllerLogin {
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
	public void iniciarSesion() {

		String usuario = jfxtUsuario.getText();
		String password = GsonGeneral.getMd5(jfxtPassword.getText());
		List<Persona> lista = GsonGeneral.desserializarJsonAArray();
		int cont = 0;
		boolean rompebucle = true;
		try {

			while (rompebucle) {
				if (cont == lista.size()) {
					rompebucle = false;
				}
				if (usuario.equals(lista.get(cont).getUsuario()) && password.equals(lista.get(cont).getPassword())) {
					String tUsu = lista.get(cont).getTipoUsuario();
					// esta line es para cerrar la ventana anterior
					Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
					stage.close();
					switch (tUsu) {
					case "paciente":
						pacienteHome(lista.get(cont));
						break;
					case "medico":
						medicoHome(lista.get(cont));
						break;
					case "cuidador":
						cuidadorHome(lista.get(cont));
						break;
					default:
						break;
					}
					rompebucle = false;
				} else {
					lbError.setText("Error: Usuario o password INCORRECTO.");
				}
				cont++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void pacienteHome(Persona p) {
		try {
			String vistaPaciente = "/View/HomePaciente.fxml";
			String tituloVista = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ControllerHomePaciente controllerHomePaciente = new ControllerHomePaciente();
			loader.setController(controllerHomePaciente);
			Parent root2 = loader.load();
			controllerHomePaciente.writeText(p);
			Stage stage2 = new Stage();
			stage2.setMaximized(true);
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

	public void medicoHome(Persona p) {
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControllerHomeMedico controlerMedicoHome = new ControllerHomeMedico();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
			controlerMedicoHome.writeText(p);
			Stage stage2 = new Stage();
			stage2.setMaximized(true);
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

	public void cuidadorHome(Persona p) {
		try {
			String vistaCuidador = "/View/VistaCuidador1.fxml";
			String tituloVista3 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaCuidador));
			ControllerHomeCuidador vistaCuidadorPrincipalController = new ControllerHomeCuidador();
			loader.setController(vistaCuidadorPrincipalController);
			Parent root2 = loader.load();
			vistaCuidadorPrincipalController.writeText(p);
			Stage stage2 = new Stage();
			stage2.setMaximized(true);
			stage2.setTitle(tituloVista3);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la
																									// vista
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root2));
			stage2.show();
			vistaCuidadorPrincipalController.cargarTableview(p);
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
			Scene scene = new Scene(page, screenSize.width, screenSize.height);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setScene(scene);
			sendStage.setMaximized(true);
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
