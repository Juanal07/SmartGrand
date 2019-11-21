package Model;

import java.util.ArrayList;

public class Clinico extends Persona {

	private String correoElectronico;
	private String especialidad;
	private String centroMedico;
	private ArrayList<String> dniPacientes;

	public Clinico(String usuario, String password, String nombre, String apellido, String tipoUsuario, String dni,
			String correoElectronico, String especialidad, String centroMedico, ArrayList<String> dniPacientes) {
		super(usuario, password, nombre, apellido, tipoUsuario, dni);
		this.correoElectronico = correoElectronico;
		this.especialidad = especialidad;
		this.centroMedico = centroMedico;
		this.dniPacientes = dniPacientes;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCentroMedico() {
		return centroMedico;
	}

	public void setCentroMedico(String centroMedico) {
		this.centroMedico = centroMedico;
	}

	public ArrayList<String> getDniPacientes() {
		return dniPacientes;
	}

	public void setDniPacientes(ArrayList<String> dniPacientes) {
		this.dniPacientes = dniPacientes;
	}
}