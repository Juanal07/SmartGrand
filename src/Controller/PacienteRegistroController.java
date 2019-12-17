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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Controller.GsonGeneral;
import Model.Cuidador;
import Model.Medico;
import Model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class PacienteRegistroController {
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
	public TextField tfUsuario = new JFXTextField(), tfNombre = new JFXTextField(), tfApellido = new JFXTextField(),
			tfDni = new JFXTextField();
	@FXML
	public PasswordField tfPassword = new PasswordField();
	
	@FXML
	private ComboBox<String> cuidadoresDisponiblesBox;
	
	ObservableList<String> cuidadoresDisponiblesList = FXCollections.observableArrayList();
	@FXML
	private ComboBox<String> medicosDisponiblesBox;
	
	ObservableList<String> medicosDisponiblesList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		cuidadoresDisponiblesList.addAll(listaCuidadoresDisponiblesNombre(listaCuidadoresDisponibles()));
		cuidadoresDisponiblesBox.setValue("Elige cuidador");
		cuidadoresDisponiblesBox.setItems(cuidadoresDisponiblesList);	
		medicosDisponiblesList.addAll(listaMedicosDisponiblesNombre(listaMedicosDisponibles()));
		medicosDisponiblesBox.setValue("Elige medico");
		medicosDisponiblesBox.setItems(medicosDisponiblesList);
	}
	//devuelve los dnis de los cuidadores disponibles
	public ArrayList<String> listaCuidadoresDisponibles() {
		ArrayList<String> listaCuidadoresDisponibles = new ArrayList<String>();
		List<Cuidador> listaCuidadorTotal = GsonGeneral.desserializarJsonAArrayCuidador();
		for (Cuidador cuidador : listaCuidadorTotal) {
			if (cuidador.getDniPacientes().size() < 4) { //si el cuidador tiene menos de 4 pacientes lo denominamos "disponible"
				listaCuidadoresDisponibles.add(cuidador.getIdCuidador());
			}				
		}		
		return listaCuidadoresDisponibles; //devuelve los dnis		
	}
	//dados unos dnis de cuidadores devuelve los nombres
	public ArrayList<String> listaCuidadoresDisponiblesNombre(ArrayList<String> a){
		ArrayList<String> listaCuidadoresDisponiblesNombre = new ArrayList<String>();
		List<Persona> listaCuidadorTotalNombre = GsonGeneral.desserializarJsonAArray();
		for (Persona p : listaCuidadorTotalNombre) {
			int sizeArray = a.size();
			int i = 0;
			while(i < sizeArray) {
				if (p.getDni().equals(a.get(i))) {
					listaCuidadoresDisponiblesNombre.add(p.getNombre() + " " +p.getApellido());
					i = i + sizeArray;
				}
				i++;	
			}	
		}		
		return listaCuidadoresDisponiblesNombre;
	}
	//devuelve los dnis de los medicos disponibles
	public ArrayList<String> listaMedicosDisponibles() {
		ArrayList<String> listaMedicosDisponibles = new ArrayList<String>();
		List<Medico> listaMedicoTotal = GsonGeneral.desserializarJsonAArrayMedico();
		for (Medico medico : listaMedicoTotal) {
			if (medico.getDniPacientes().size() < 4) { //si el Medico tiene menos de 4 pacientes lo denominamos "disponible"
				listaMedicosDisponibles.add(medico.getIdMedico());
			}				
		}		
		return listaMedicosDisponibles; //devuelve los dnis		
	}
	//dados unos dnis de Medicos devuelve los nombres
	public ArrayList<String> listaMedicosDisponiblesNombre(ArrayList<String> a){
		ArrayList<String> listaMedicosDisponiblesNombre = new ArrayList<String>();
		List<Persona> listaMedicoTotalNombre = GsonGeneral.desserializarJsonAArray();
		for (Persona p : listaMedicoTotalNombre) {
			int sizeArray = a.size();
			int i = 0;
			while(i < sizeArray) {
				if (p.getDni().equals(a.get(i))) {
					listaMedicosDisponiblesNombre.add(p.getNombre() + " " +p.getApellido());
					i = i + sizeArray;
				}
				i++;	
			}	
		}		
		return listaMedicosDisponiblesNombre;
	}
	
	@FXML
	public void pacienteRegistrado(ActionEvent actionEvent) throws IOException {		
		
		String usuario = tfUsuario.getText();
		String password2 = tfPassword.getText();//password sin cifrar para hacer el validation
		String password = GsonGeneral.getMd5(tfPassword.getText());
		String nombre = tfNombre.getText();
		String apellido = tfApellido.getText();
		String dni = tfDni.getText();
		String tipoUsuario = "paciente";
	
		boolean valido = validation(usuario, password2, nombre, apellido, tipoUsuario, dni);
		
		if(usuario != "" && password != "" && nombre != "" && apellido != "" && dni != "" && valido) {

		Persona nuevo = new Persona(usuario, password, nombre, apellido, tipoUsuario, dni);
		List<Persona> lista = GsonGeneral.desserializarJsonAArray(); // Creamos lista de personas con la info del json
		lista.add(nuevo); // añadimos el nuevo usuario a la lista
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create(); // Pasamos la lista a formato json
		String representacionBonita = prettyGson.toJson(lista);
		String ruta = "usuarios.json";
		GsonGeneral.EscribirJson(representacionBonita, ruta);
		
		Stage stage = (Stage) btnRegistrarse.getScene().getWindow(); // cerramos ventana
		stage.close();
		String vistaRegPac = "/View/Login.fxml"; // creamos la nueva
		String tituloVista = "Login";
		LoginControler loginControler = new LoginControler();
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
	// revisar
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
		
		if (dni.length() != 9) { //^(([A-Z]\\d{8})|(\\d{8}[A-Z]))$
			lbErrorDni.setText("Error! DNI incorrecto.");
			valido = false;
			lbErrorDni.setText("");
			//apellido = tfDni.getText().intern();
		} else {
			lbErrorDni.setText("");
	
		}
		
		return valido;
	}
	
	
	
	
}
