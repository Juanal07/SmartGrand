package model;

public class Sensor2puerta {
	
	private String idPaciente;
	private boolean[] estadoPuerta; //cada 10 min checkea el estado de la puerta para visualizar lo que ha pasado las ultimas 24 horas abria que consultar las ultimas 144 posiciones del array
	
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
	
	public static String comoEsta(String dni) {
		String palabra = null;
		palabra = "abierta";
		
		
		return palabra;
		
		
	}

}
