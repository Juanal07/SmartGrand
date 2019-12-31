package model;

public class Sensor3caidas {
	private String idPaciente;
	private int[] caidas;
	
	public Sensor3caidas(String idPaciente, int[] caidas) {
		super();
		this.idPaciente = idPaciente;
		this.caidas = caidas;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int[] getCaidas() {
		return caidas;
	}
	public void setCaidas(int[] caidas) {
		this.caidas = caidas;
	}

}
