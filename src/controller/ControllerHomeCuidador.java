package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DataBase.Conexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Cuidador;
import model.Medico;
import model.Persona;
import model.PersonaNew;
public class ControllerHomeCuidador {
	@FXML
	private TableView<PersonaNew> tablaPacientesCuidador;
	@FXML
	private TableColumn<PersonaNew, String> colDNI;
	@FXML
	private TableColumn<PersonaNew, String> colNombre;
	@FXML
	private TableColumn<PersonaNew, String> colApellidos;
	@FXML
	private TableColumn<PersonaNew, String> colTiposuario;
	@FXML
	private Label lbOculto = new Label();
	@FXML
	public Button btnCerrarSesion;
	@FXML
	private Label idCuidadorLabel = new Label();
	
	public void writeText(PersonaNew persona) {
		idCuidadorLabel.setText("Cuidador: Bienvenido/a " + persona.getNombre() + "\nelije uno de tus pacientes\n para ver su sensor de presion");
		lbOculto.setText(persona.getDni());
	}
	//dado un cuidador p ponemos en un observableList sus pacientes
	public void leerPersonas(ObservableList<Persona> listaPersonas, PersonaNew persona) {
		ArrayList<String> listaMisPacientes;
		listaMisPacientes = listaPacientes(persona);
		List<Persona> lista = GsonGeneral.desserializarJsonAArray();
		int lenthArray = listaMisPacientes.size();
		// recorremos personas
		for (int i = 0; i < lista.size(); i++) {
			int j = 0;
			// recorremos mis pacientes
			while (j < lenthArray) {
				if (lista.get(i).getDni().equals(listaMisPacientes.get(j))) {
					listaPersonas.add(lista.get(i));
					j = j + lenthArray;
				}
				j++;
			}
		}
	}
	//dado un cuidador p obtengo un arrayList con los dnis de sus pacientes
	public ArrayList<String> listaPacientes(PersonaNew persona) {
		ArrayList<String> idsPacientes = new ArrayList<String>();
		List<Cuidador> listaCuidadorRelacion = GsonGeneral.desserializarJsonAArrayCuidador();
		int sizeArray = listaCuidadorRelacion.size();
		int i = 0;
		while(i < sizeArray) {
			if (persona.getDni().equals(listaCuidadorRelacion.get(i).getIdCuidador())) {
				idsPacientes = listaCuidadorRelacion.get(i).getDniPacientes();
				i = i + sizeArray;
			}
			i++;	
		}	
		return idsPacientes;
	}		
	@FXML
	public void cerrarSesion(ActionEvent actionEvent) {
		// cerramos ventana
		Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
		stage.close();
		// creamos la nueva
		String vistaRegContinuo = "/View/Login.fxml";
		String tituloVista = "Login";
		ControllerLogin loginControler = new ControllerLogin();
		crearVentana(vistaRegContinuo, tituloVista, loginControler);
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
	public void cargarTableview (PersonaNew persona) {
		ObservableList<PersonaNew> listaPersonas = FXCollections.observableArrayList();

		Conexion conexion = new Conexion();
		java.util.List<PersonaNew> lista = conexion.listaPacientesCuidador(persona.getId_per());

		for (PersonaNew p : lista) {
			System.out.println(p.getUsuario());
			listaPersonas.add(p);
		}
		

		tablaPacientesCuidador.setItems(listaPersonas);
		colDNI.setCellValueFactory(new PropertyValueFactory<PersonaNew, String>("dni"));
		colNombre.setCellValueFactory(new PropertyValueFactory<PersonaNew, String>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<PersonaNew, String>("apellido"));	
		
//		tablaPacientesCuidador.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PersonaNew>() {
//			@Override
//			public void changed(ObservableValue<? extends PersonaNew> observable, Persona oldValue, Persona newValue) {
//				Persona persona = tablaPacientesCuidador.getSelectionModel().getSelectedItem();
//				enviarSensor1(persona.getDni());
//			
//			}				
//		});	
	}

	public void enviarSensor1(String dni) {
		String dniPaciente = dni;
		try {
			ControllerSensor1Presion controlBarChart = new ControllerSensor1Presion();
			FXMLLoader root2 =  new FXMLLoader();
			root2.setLocation(this.getClass().getResource("/View/sensor1Presion.fxml"));
			root2.setController(controlBarChart);
			AnchorPane page = (AnchorPane) root2.load();
			Stage sendStage = new Stage();
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			sendStage.getIcons().add(icon);
			sendStage.setTitle("Sensor Presion");
			Scene scene = new Scene(page);		
			sendStage.setScene(scene);
			sendStage.show();
			controlBarChart.escibirDniPaciente(dniPaciente);
			controlBarChart.cargarGrafica();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}