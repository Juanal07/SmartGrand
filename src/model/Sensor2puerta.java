package model;

public class Sensor2puerta {
	
	private String idPaciente;
	private int[] estadoPuerta; //cada hora checkea el estado de la puerta para visualizar lo que ha pasado las ultimas 24 horas 
	
	public Sensor2puerta(String idPaciente, int[] estadoPuerta) {
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
	public int[] getEstadoPuerta() {
		return estadoPuerta;
	}
	public void setEstadoPuerta(int[] estadoPuerta) {
		this.estadoPuerta = estadoPuerta;
	}
	
	public static String comoEsta(String dni) {
		String palabra = null;
		palabra = "abierta";
		
		
		return palabra;
		
		
	}

}
