package Model;

import java.util.ArrayList;

public class Cuidador extends Persona {

	private String direccion;
	private String correoElectronico;
	private ArrayList<String> dniPacientes;
	private int telefono;

	public Cuidador(String usuario, String password, String nombre, String apellido, String tipoUsuario, String dni,
			String direccion, String correoElectronico, ArrayList<String> dniPacientes, int telefono) {
		super(usuario, password, nombre, apellido, tipoUsuario, dni);
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.dniPacientes = dniPacientes;
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public ArrayList<String> getDniPacientes() {
		return dniPacientes;
	}

	public void setDniPacientes(ArrayList<String> dniPacientes) {
		this.dniPacientes = dniPacientes;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}