package model;

public class Sensor2puerta {
	
	private String idPaciente;
	private boolean[] estadoPuerta;
	
	public Sensor2puerta(String idPaciente, boolean[] estadoPuerta) {
		super();
		this.idPaciente = idPaciente;
		this.estadoPuerta = estadoPuerta;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public boolean[] getEstadoPuerta() {
		return estadoPuerta;
	}
	public void setEstadoPuerta(boolean[] estadoPuerta) {
		this.estadoPuerta = estadoPuerta;
	}	

}
