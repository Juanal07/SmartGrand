package Model;

public class Tickets {
	private String idPaciente;
	private String idClinico;
	private String textoPaciente;
	private String TextoClinico;

	public Tickets(String idPaciente, String idClinico, String textoPaciente, String textoClinico) {
		super();
		this.idPaciente = idPaciente;
		this.idClinico = idClinico;
		this.textoPaciente = textoPaciente;
		this.TextoClinico = textoClinico;
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
		return TextoClinico;
	}

	public void setTextoClinico(String textoClinico) {
		TextoClinico = textoClinico;
	}

}
