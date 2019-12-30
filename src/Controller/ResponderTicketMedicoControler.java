package Controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import Model.Persona;
import Model.Tickets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ResponderTicketMedicoControler {
	@FXML
	private JFXTextArea txAreaMedico = new JFXTextArea();
	@FXML
	private JFXButton btnEnviar = new JFXButton();
	@FXML
	private JFXScrollPane jfxScrollPane = new JFXScrollPane();
	@FXML
	private Label lbTextoPaciente = new Label();
	@FXML
	private Label lbError = new Label();

	private Label lbOculto = new Label();
	private Label lbOculto2 = new Label();

	public void enviarRespuesta() {
		if (txAreaMedico.getText().equals("")) {
			lbError.setText("Error: No puede enviar un Ticket vacio.");
		} else {
			String txPaciente = lbTextoPaciente.getText();
			String txtMedico = txAreaMedico.getText();
			String idPaciente;
			String idClinico;
			String[] cadenas = lbOculto.getText().trim().split("\t");
			idPaciente = cadenas[0];
			idClinico = cadenas[1];
			leerTickets(idPaciente, idClinico, txPaciente, txtMedico);
		}
	}

	public void vaciarLb() {
		lbError.setText("");
		txAreaMedico.setText("");
	}

	public void leerTickets(String idPaciente, String idClinico, String txPaciente, String txtMedico) {
		List<Tickets> tiquets = GsonGeneral.desserializarJsonAArrayTicket();
		int sizeTiquets = tiquets.size();
		int aux = 0;
		while (aux < sizeTiquets) {
			Tickets t = tiquets.get(aux);
			String idP = t.getIdPaciente();
			String idC = t.getIdClinico();
			String txP = t.getTextoPaciente();
			if (idP.equals(idPaciente) && idClinico.equals(idC) && txP.equals(txPaciente)) {
				t.setTextoClinico(txtMedico);
				aux = aux + sizeTiquets;
			}
			aux++;
		}

		reescibirTicket(tiquets);
	}

	public void reescibirTicket(List<Tickets> tiquets) {
		String ruta = "jsonTickets.json";
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(tiquets);
		GsonGeneral.EscribirJson(representacionBonita, ruta);
		ventanaHomeMedico();
	}

	public void ventanaHomeMedico() {
		Stage stage = (Stage) lbTextoPaciente.getScene().getWindow();
		stage.close();

		String[] cadenaPersona = lbOculto2.getText().split("\t");
		String usu = cadenaPersona[0];
		String pass = cadenaPersona[1];
		String nom = cadenaPersona[2];
		String apell = cadenaPersona[3];
		String tp = cadenaPersona[4];
		String dni = cadenaPersona[5];
		Persona p = new Persona(usu, pass, nom, apell, tp, dni);
		try {
			String vistaMedico = "/View/MedicoHome.fxml";
			String tituloVista2 = "Bienvenido: " + p.getNombre() + " " + p.getApellido();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaMedico));
			ControlerMedicoHome controlerMedicoHome = new ControlerMedicoHome();
			loader.setController(controlerMedicoHome);
			Parent root1 = loader.load();
			Stage stage2 = new Stage();
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

	public void writeText(Tickets ticket, String persona) {
		lbTextoPaciente.setWrapText(true);
		lbTextoPaciente.setText(ticket.getTextoPaciente());
		jfxScrollPane.setContent(lbTextoPaciente);

		lbOculto.setText(ticket.toString());
		lbOculto2.setText(persona);
	}

}
