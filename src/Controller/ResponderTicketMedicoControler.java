package Controller;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import Model.Tickets;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResponderTicketMedicoControler {
	@FXML
	private JFXTextArea txAreaMedico = new JFXTextArea();
	@FXML
	private JFXButton btnEnviar = new JFXButton();
	@FXML
	private Label lbTextoPaciente = new Label();
	private Label lbOculto = new Label();

	public void enviarRespuesta() {
		String txPaciente = lbTextoPaciente.getText();
		String txtMedico = txAreaMedico.getText();
		String idPaciente;
		String idClinico;
		String[] cadenas = lbOculto.getText().trim().split("\t");
		for (String string : cadenas) {
			System.out.println(string);
		}
		idPaciente = cadenas[0];
		idClinico = cadenas[1];
		leerTickets(idPaciente, idClinico, txPaciente, txtMedico);
		
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
			aux ++;
		}
		
		reescibirTicket(tiquets);
	}

	public void reescibirTicket(List<Tickets> tiquets) {
		String ruta = "jsonTickets.json";
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String representacionBonita = prettyGson.toJson(tiquets);
		System.out.println(representacionBonita);
		GsonGeneral.EscribirJson(representacionBonita, ruta);
		ventanaHomeMedico();
	}

	public void ventanaHomeMedico() {
		
		
	}

	public void writeText(Tickets ticket) {
		lbTextoPaciente.setText(ticket.getTextoPaciente());
		lbOculto.setText(ticket.toString());
	}

}
