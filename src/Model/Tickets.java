package Model;

import java.time.LocalDate;

public class Tickets {
	private String idPaciente;
	private String idClinico;
	private String textoPaciente;
	private String textoClinico;
	//private LocalDate date;

	public Tickets(String idPaciente, String idClinico, String textoPaciente, String textoClinico) {
		super();
		this.idPaciente = idPaciente;
		this.idClinico = idClinico;
		this.textoPaciente = textoPaciente;
		this.textoClinico = textoClinico;
		//this.date = date;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdClinico() {
		return idClinico;
	}

	public void setIdClinico(String idClinico) {
		this.idClinico = idClinico;
	}

	public String getTextoPaciente() {
		return textoPaciente;
	}

	public void setTextoPaciente(String textoPaciente) {
		this.textoPaciente = textoPaciente;
	}

	public String getTextoClinico() {
		return textoClinico;
	}

	public void setTextoClinico(String textoClinico) {
		this.textoClinico = textoClinico;
	}

	@Override
	public String toString() {
		return "Tickets [idPaciente=" + idPaciente + ", idClinico=" + idClinico + ", textoPaciente=" + textoPaciente
				+ ", textoClinico=" + textoClinico + "]";
	}
}
