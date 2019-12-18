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
	private Label lbErrorCuidador;
	@FXML
	private Label lbErrorMedico;
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
		for (Persona p : listaCuidadoresDisponibles()) {
			cuidadoresDisponiblesList.add(p.getDni() + ": " + p.getNombre() + " " + p.getApellido());
		}
		cuidadoresDisponiblesBox.setValue("Elige cuidador");
		cuidadoresDisponiblesBox.setItems(cuidadoresDisponiblesList);
		
		for (Persona p : listaMedicosDisponibles()) {
			medicosDisponiblesList.add(p.getDni() + ": " + p.getNombre() + " " + p.getApellido());
		}
		medicosDisponiblesBox.setValue("Elige medico");
		medicosDisponiblesBox.setItems(medicosDisponiblesList);
	}
	//devuelve los cuidadores disponibles
	public ArrayList<Persona> listaCuidadoresDisponibles() {
		ArrayList<String> listaCuidadoresDisponiblesDni = new ArrayList<String>();
		List<Cuidador> listaCuidadorTotal = GsonGeneral.desserializarJsonAArrayCuidador();
		for (Cuidador cuidador : listaCuidadorTotal) {
			if (cuidador.getDniPacientes().size() < 4) { //si el cuidador tiene menos de 4 pacientes lo denominamos "disponible"
				listaCuidadoresDisponiblesDni.add(cuidador.getIdCuidador());
			}				
		}	
		ArrayList<Persona> listaCuidadoresDisponiblesPersona = new ArrayList<Persona>();
		List<Persona> listaCuidadorTotalNombre = GsonGeneral.desserializarJsonAArray();
		for (Persona p : listaCuidadorTotalNombre) {
			int sizeArray = listaCuidadoresDisponiblesDni.size();
			int i = 0;
			while(i < sizeArray) {
				if (p.getDni().equals(listaCuidadoresDisponiblesDni.get(i))) {
					listaCuidadoresDisponiblesPersona.add(p);
					i = i + sizeArray;
				}
				i++;	
			}	
		}		
		return listaCuidadoresDisponiblesPersona; 
	}
	
	//devuelve los medicos disponibles
	public ArrayList<Persona> listaMedicosDisponibles() {
		ArrayList<String> listaMedicosDisponibles = new ArrayList<String>();
		List<Medico> listaMedicoTotal = GsonGeneral.desserializarJsonAArrayMedico();
		for (Medico medico : listaMedicoTotal) {
			if (medico.getDniPacientes().size() < 4) { //si el Medico tiene menos de 4 pacientes lo denominamos "disponible"
				listaMedicosDisponibles.add(medico.getIdMedico());
			}				
		}	
		ArrayList<Persona> listaMedicosDisponiblesPersona = new ArrayList<Persona>();
		List<Persona> listaMedicoTotalNombre = GsonGeneral.desserializarJsonAArray();
		for (Persona p : listaMedicoTotalNombre) {
			int sizeArray = listaMedicosDisponibles.size();
			int i = 0;
			while(i < sizeArray) {
				if (p.getDni().equals(listaMedicosDisponibles.get(i))) {
					listaMedicosDisponiblesPersona.add(p);
					i = i + sizeArray;
				}
				i++;	
			}	
		}	
		return listaMedicosDisponiblesPersona; //devuelve los dnis		
	}
	
	public void escribirJsonCuidadores(String dni) {
		String SubCadena = cuidadoresDisponiblesBox.getValue().substring(0,9);
		List<Cuidador> listaC = GsonGeneral.desserializarJsonAArrayCuidador();
		int sizeArray = listaC.size();
		int i = 0;
		while (i < sizeArray) {
			if (SubCadena.equals(listaC.get(i).getIdCuidador())) {
				listaC.get(i).getDniPacientes().add(dni);
				i = i + sizeArray;
			}
			i++;
		}	
		Gson prettyGson2 = new GsonBuilder().setPrettyPrinting().create(); 
		String representacionBonita2 = prettyGson2.toJson(listaC);
		String ruta2 = "cuidadores.json";
		GsonGeneral.EscribirJson(representacionBonita2, ruta2);
		
	}
	
	public void escribirJsonMedicos(String dni) {
		
		String SubCadena3 = medicosDisponiblesBox.getValue().substring(0,9);
		List<Medico> listaM = GsonGeneral.desserializarJsonAArrayMedico();
		int sizeArray3 = listaM.size();
		int j = 0;
		while (j < sizeArray3) {
			if (SubCadena3.equals(listaM.get(j).getIdMedico())) {
				listaM.get(j).getDniPacientes().add(dni);
				j = j + sizeArray3;
			}
			j++;
		}	
		Gson prettyGson3 = new GsonBuilder().setPrettyPrinting().create(); 
		String representacionBonita3 = prettyGson3.toJson(listaM);
		String ruta3 = "medicos.json";
		GsonGeneral.EscribirJson(representacionBonita3, ruta3);	
		
		
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
		String cuidadorE = cuidadoresDisponiblesBox.getValue();
		String medicoE = medicosDisponiblesBox.getValue();

		boolean valido = validation(usuario, password2, nombre, apellido, tipoUsuario, dni, cuidadorE, medicoE);
		
		if(usuario != "" && password != "" && nombre != "" && apellido != "" && dni != "" && valido) {
			
		escribirJsonCuidadores(dni);
		escribirJsonMedicos(dni);

		Persona nuevo = new Persona(usuario, password, nombre, apellido, tipoUsuario, dni);
		List<Persona> lista = GsonGeneral.desserializarJsonAArray(); // Creamos lista de personas con la info del json
		lista.add(nuevo); // aÃ±adimos el nuevo usuario a la lista
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
		System.out.println(cuidadoresDisponiblesBox.getValue());
		System.out.println(medicosDisponiblesBox.getValue());
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

	public boolean validation(String usuario, String password, String nombre, String apellido, String tipoUsuario,
			String dni, String cuidadorE, String medicoE) {
		boolean valido = true;
		
		if ((dni.matches("\\d{8}[A-HJ-NP-TV-Z]"))) {
			lbErrorDni.setText("");	
			if (!GsonGeneral.seRepiteDni(dni)) {
				lbErrorDni.setText("");	
			}else {
				lbErrorDni.setText("El DNI ya esta registrado");
				valido = false;		
			}
			if (GsonGeneral.validarNIF(dni)) {
				lbErrorDni.setText("");	
			}else {
				lbErrorDni.setText("El DNI no existe");
				valido = false;		
			}
		}else {
			lbErrorDni.setText("El DNI debe llevar 8 numeros y una letra mayuscula");
			valido = false;		
		}	

		if (usuario.matches("^[a-zA-Z0-9._-]{3,}$")) {
			lbErrorUsuario.setText("");
			if (!GsonGeneral.seRepiteUsuario(usuario)) {
				lbErrorUsuario.setText("");	
			}else {
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
			lbErrorPassword.setText("La contraseña debe contener al menos 8 letras, numeros o caracteres");
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
		if (!cuidadorE.matches("Elige cuidador")) {
			lbErrorCuidador.setText("");
		} else {
			lbErrorCuidador.setText("Error! Elige un cuidador.");
			valido = false;
		}
		if (!medicoE.matches("Elige medico")) {
			lbErrorMedico.setText("");
		} else {
			lbErrorMedico.setText("Error! Elige un medico.");
			valido = false;
		}
		

		
		return valido;
	}
	
	
	
	
}
