package model;

public class Sensor1New {
	private int id_sen;
	private String Ubicacion;
	private String Tipo;
	private int Dato;
	private String Fecha;
	private int id_paciente;

	public Sensor1New(int id_sen, String ubicacion, String tipo, int dato, String fecha, int id_paciente) {
		super();
		this.id_sen = id_sen;
		this.Ubicacion = ubicacion;
		this.Tipo = tipo;
		this.Dato = dato;
		this.Fecha = fecha;
		this.id_paciente = id_paciente;
	}

	public Sensor1New() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_sen() {
		return id_sen;
	}

	public void setId_sen(int id_sen) {
		this.id_sen = id_sen;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public int getDato() {
		return Dato;
	}

	public void setDato(int dato) {
		Dato = dato;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

}
