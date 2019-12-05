package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import Model.Persona;
import Model.Tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControlerMedicoHome implements Initializable{
	@FXML
	private JFXListView<String> listaPacientesMedico = new JFXListView<String>();
	@FXML
	private JFXListView<String> listaTicketsSinResponder = new JFXListView<String>();
	@FXML
	private Label labelDniMedico = new Label();
	
	private ObservableList<String> pacientesObservableList;
	
	private ObservableList<String> ticketsObservableList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> ticketsObservableList = FXCollections.observableArrayList();
		leerTickets(ticketsObservableList);
		listaTicketsSinResponder.setItems(ticketsObservableList);
		
		listaTicketsSinResponder.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String txtPaciente = listaTicketsSinResponder.getSelectionModel().getSelectedItem();
				int finalCadena = txtPaciente.indexOf("Medico:");
				String textoPaciente = txtPaciente.substring(0, finalCadena);
				System.out.println("texto Seleccionado: " + textoPaciente);
				enviarVentana(textoPaciente);
			}

			
		} );
	}
	
	public void enviarVentana(String textoPaciente) {
		String vistaPaciente = "/View/ResponderTicketMedico.fxml";
		String tituloVista = "responder tiquet.";
		
		Stage stage = (Stage) labelDniMedico.getScene().getWindow();
		stage.close();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaPaciente));
			ResponderTicketMedicoControler responderTicketMedicoControler = new ResponderTicketMedicoControler();
			loader.setController(responderTicketMedicoControler);	
			Parent root2 = loader.load();
			responderTicketMedicoControler.writeText(textoPaciente);
			Stage stage2 = new Stage();
			
	        stage2.setTitle(tituloVista);
	        stage2.setScene(new Scene(root2));
	        stage2.show();
		}catch(Exception e) {
   			e.printStackTrace();
   		}	
	}

	private void leerTickets(ObservableList<String> ticketsObservableList2) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		for (Tickets tickets : tiquets) {
			if (tickets.getTextoClinico().equals("")) {
				String ticket = "Paciente: " + tickets.getTextoPaciente() + "	Medico: " + tickets.getTextoClinico(); 
				//System.out.println(" ****************************** ");
				ticketsObservableList2.add(ticket);
				//System.out.println(ticket);
			}
		}
	}

	public void writeText(Persona p) {
		labelDniMedico.setText(p.getDni());
	}
}
