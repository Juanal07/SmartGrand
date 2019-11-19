package Model;

public class Tickets {
	private String idPaciente;
	private String idClinico;
	private String textoPaciente;
	private String TextoClinico;
	private boolean visiblePaciente;

	public Tickets(String idPaciente, String idClinico, String textoPaciente, String textoClinico,
			boolean visiblePaciente) {
		super();
		this.idPaciente = idPaciente;
		this.idClinico = idClinico;
		this.textoPaciente = textoPaciente;
		this.TextoClinico = textoClinico;
		this.visiblePaciente = visiblePaciente;
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

	public boolean isVisiblePaciente() {
		return visiblePaciente;
	}

	public void setVisiblePaciente(boolean visiblePaciente) {
		this.visiblePaciente = visiblePaciente;
	}

}
