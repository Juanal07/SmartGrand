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
		String password = GsonGeneral.getMd5(jfxtPassword.getText());
		//System.out.println("Usuario: " + usuario + " -> password: " + password);
		java.util.List<Persona> lista = GsonGeneral.desserializarJsonAArray();
		for (Persona p : lista) {
			//System.out.println("usuario json: " + p.getUsuario() + " -> password: " + p.getPassword());
			if (usuario.equals(p.getUsuario()) && password.equals(p.getPassword())) {
				//System.out.println("Si esta el usuario");
				String tUsu = p.getTipoUsuario();
				// esta line es para cerrar la ventana anterior
				Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
				stage.close();
				switch (tUsu) {
				case "paciente":
					// creamos la ventana
					String vistaPaciente = "/View/HomePaciente.fxml";
					String tituloVista = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					//ControllerHomePaciente controllerHomePaciente = new ControllerHomePaciente();
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
						ControllerHomePaciente controllerHomePaciente = new ControllerHomePaciente();
						loader.setController(controllerHomePaciente);	
						Parent root2 = loader.load();
						controllerHomePaciente.writeText(p);
						Stage stage2 = new Stage();
				        stage2.setTitle(tituloVista);
				        Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la vista	
						stage2.getIcons().add(icon);
				        stage2.setScene(new Scene(root2));
				        stage2.show();

					
					}catch(Exception e) {
			   			e.printStackTrace();
			   		}	

					break;
				case "medico":
					// creamos la ventana
					String vistaMedico = "/View/MedicoHome.fxml";
					String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
						ControlerMedicoHome controlerMedicoHome = new ControlerMedicoHome();
						loader.setController(controlerMedicoHome);	
						Parent root1 = loader.load();
						controlerMedicoHome.writeText(p);
						Stage stage2 = new Stage();
				        stage2.setTitle(tituloVista2);
				        Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la vista	
						stage2.getIcons().add(icon);
				        stage2.setScene(new Scene(root1));
				        stage2.show();
					}catch(Exception e) {
			   			e.printStackTrace();
			   		}	
					break;
				case "cuidador":
					// creamos la ventana
					String vistaCuidador = "/View/VistaCuidador1.fxml";
					String tituloVista3 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
					
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaCuidador));
						VistaCuidador1Controller vistaCuidadorPrincipalController = new VistaCuidador1Controller();
						loader.setController(vistaCuidadorPrincipalController);	
						Parent root2 = loader.load();
						vistaCuidadorPrincipalController.writeText(p);
						Stage stage2 = new Stage();
				        stage2.setTitle(tituloVista3);
				        Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // annade icono a la vista	
						stage2.getIcons().add(icon);
				        stage2.setScene(new Scene(root2));
				        stage2.show();

					
					}catch(Exception e) {
			   			e.printStackTrace();
			   		}	
					break;

				default:
					break;
				}
				break;
			} else {
				//System.out.println("Error: Usuario o password INCORRECTO.");
				lbError.setText("Error: Usuario o password INCORRECTO.");
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
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png")); // a�ade icono a la vista	
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
