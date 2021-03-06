package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import dataBase.Conexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PacienteNew;
import model.PersonaNew;
import model.TicketsNew;

public class ControllerHomeMedico {
	@FXML
	private JFXListView<PacienteNew> listaPacientesMedico = new JFXListView<PacienteNew>();
	@FXML
	private JFXListView<TicketsNew> listaTicketsSinResponder = new JFXListView<TicketsNew>();
	@FXML
	private JFXButton btnCerrarSesion = new JFXButton();
	private Label lbIdPerMedico = new Label();
	@FXML
	private Label labelMedico = new Label();

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

	public void writeText(PersonaNew p) {
		labelMedico.setText("Medico: Bienvenido/a " + p.getNombre());
		// le pasamos el id del medico
		lbIdPerMedico.setText(Integer.toString(p.getId_per()));
	}

	private void crearVentana(String vista, String titulo, Object object) {
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

	public void leerPersonas(ObservableList<PacienteNew> pacientesObservableList2, PersonaNew p) {
		Conexion conexion = new Conexion();
		conexion.consultaPacientes(p.getId_per(), pacientesObservableList2);
	}

	public void enviarVentana(TicketsNew ticket, PersonaNew p) {
		String vistaPaciente = "/View/ResponderTicketMedico.fxml";
		String tituloVista = "responder tiquet.";
		Stage stage = (Stage) listaTicketsSinResponder.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ControllerResponderTicketMedico responderTicketMedicoControler = new ControllerResponderTicketMedico();
			loader.setController(responderTicketMedicoControler);
			Parent root2 = loader.load();
			// ticket seleccionado y persona medico
			responderTicketMedicoControler.writeText(ticket, p);
			Stage stage2 = new Stage();
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root2,1280,720));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ObservableList<TicketsNew>  leerTickets2(ObservableList<TicketsNew> ticketsObservableList2, PersonaNew p) {
		Conexion conexion = new Conexion();
		conexion.leerTickets(ticketsObservableList2,"id_medico", p.getId_per());// tenemos todos los tickets
		return ticketsObservableList2;
	}

	public void cargarListViewPacientes(PersonaNew p) {
		// decidir si queremos colocar personas o pacientes
		ObservableList<PacienteNew> pacientesObservableList = FXCollections.observableArrayList();
		leerPersonas(pacientesObservableList, p);// encuentra los objetos de las personas y los mete en
		System.out.println("PRUEBA CARGAR LISTA PACIENTES");											// personasObservableList
		// comprobar que todo sale correcto
		for (PacienteNew pacienteNew : pacientesObservableList) {
			System.out.println(pacienteNew.toString());
		}
		listaPacientesMedico.setItems(pacientesObservableList);
		listaPacientesMedico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PacienteNew>() {

			@Override
			public void changed(ObservableValue<? extends PacienteNew> observable, PacienteNew oldValue,
					PacienteNew newValue) {
				PacienteNew paciente = listaPacientesMedico.getSelectionModel().getSelectedItem();
				ventanaDatosPaciente(paciente, lbIdPerMedico.getText());
			}
		});
	}

	public void ventanaDatosPaciente(PacienteNew paciente, String id_med) {
		String vistaDatosPaciente = "/View/MedicoPacienteLectura.fxml";
		String tituloVista = "Datos Paciente.";
		Stage stage = (Stage) listaTicketsSinResponder.getScene().getWindow();
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaDatosPaciente));
			ControllerMedicoDatosPaciente medicoDatosPaciente = new ControllerMedicoDatosPaciente();
			loader.setController(medicoDatosPaciente);
			Parent root = loader.load();
			medicoDatosPaciente.writeText(paciente, id_med);
			Stage stage2 = new Stage();
			Image icon = new Image(getClass().getResourceAsStream("/Image/logo sin fondo.png"));
			stage2.getIcons().add(icon);
			stage2.setTitle(tituloVista);
			stage2.setScene(new Scene(root,1280,720));
			stage2.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarListViewTickets(PersonaNew p) {
		ObservableList<TicketsNew> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets2(ticketsObservableList, p);
		System.out.println("PRUEBA CARGAR LIST TICKETS");
		for (TicketsNew ticketsNew : ticketsObservableList) {
			System.out.println(ticketsNew.toString());
		}
		listaTicketsSinResponder.setItems(ticketsObservableList);

		listaTicketsSinResponder.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<TicketsNew>() {

					@Override
					public void changed(ObservableValue<? extends TicketsNew> observable, TicketsNew oldValue,
							TicketsNew newValue) {
						TicketsNew ticket = listaTicketsSinResponder.getSelectionModel().getSelectedItem();
						enviarVentana(ticket, p);
					}
				});
	}
}
