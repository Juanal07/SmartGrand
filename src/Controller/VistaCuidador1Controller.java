package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Cuidador;
import Model.Medico;
import Model.Persona;
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

public class VistaCuidador1Controller {
	
	@FXML
	private TableView<Persona> tablaPacientesCuidador;
	@FXML
	private TableColumn<Persona, String> colDNI;
	@FXML
	private TableColumn<Persona, String> colNombre;
	@FXML
	private TableColumn<Persona, String> colApellidos;
//	@FXML
//	private TableColumn<Persona, String> colTiposuario;
	
	
	@FXML
	public Button btnCerrarSesion;

	@FXML
	private Label idCuidadorLabel = new Label();

	public void writeText(Persona p) {
		idCuidadorLabel.setText("Bienvenido " + p.getNombre() + " elije uno de tus pacientes para ver su estado");
	}
	
	//dado un cuidador p ponemos en un observableList sus pacientes
	public void leerPersonas(ObservableList<Persona> listaPersonas, Persona p) {
		ArrayList<String> listaMisPacientes;
		listaMisPacientes = listaPacientes(p);
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
	public ArrayList<String> listaPacientes(Persona p) { 
		ArrayList<String> idsPacientes = new ArrayList<String>();
		List<Cuidador> listaCuidadorRelacion = GsonGeneral.desserializarJsonAArrayCuidador();
		int sizeArray = listaCuidadorRelacion.size();
		int i = 0;
		while(i < sizeArray) {
			if (p.getDni().equals(listaCuidadorRelacion.get(i).getIdCuidador())) {
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
		LoginControler loginControler = new LoginControler();
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


	public void cargarTableview (Persona p) {
		ObservableList<Persona> listaPersonas = FXCollections.observableArrayList();
		leerPersonas(listaPersonas, p);
		tablaPacientesCuidador.setItems(listaPersonas);
		colDNI.setCellValueFactory(new PropertyValueFactory<Persona, String>("dni"));
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));	
		
		tablaPacientesCuidador.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Persona>() {

			@Override
			public void changed(ObservableValue<? extends Persona> observable, Persona oldValue, Persona newValue) {
				Persona persona = tablaPacientesCuidador.getSelectionModel().getSelectedItem();
				//Aqui habria que insertar el crear ventana de sensores	
				ventanaDatosPaciente(p);
			}				
		});	
	}
	
	public void ventanaDatosPaciente(Persona persona) {
		String vistaDatosPaciente = "/View/VistaCuidador2.fxml";
		String tituloVista = "Datos Paciente";
		Stage stage = (Stage) tablaPacientesCuidador.getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaDatosPaciente));
			VistaCuidador2Controller controller2 = new VistaCuidador2Controller();
			loader.setController(controller2);
			Parent root = loader.load();
			Stage stage2 = new Stage();
			stage2.setTitle(tituloVista);
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setScene(new Scene(root));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
	
	


