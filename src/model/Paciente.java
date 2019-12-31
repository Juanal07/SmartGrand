package model;

import java.util.ArrayList;

public class Paciente extends Persona {
	private String localidad;
	private String direccion;
	private ArrayList<String> dniCuidadores;

	public Paciente(String usuario, String password, String nombre, String apellido, String tipoUsuario, String dni,
			String localidad, String direccion, ArrayList<String> dniCuidadores) {
		super(usuario, password, nombre, apellido, tipoUsuario, dni);
		this.localidad = localidad;
		this.direccion = direccion;
		this.dniCuidadores = dniCuidadores;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ArrayList<String> getDniCuidadores() {
		return dniCuidadores;
	}

	public void setDniCuidadores(ArrayList<String> dniCuidadores) {
		this.dniCuidadores = dniCuidadores;
	}

}