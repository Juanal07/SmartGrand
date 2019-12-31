package model;

import java.util.ArrayList;

public class Medico {

	private String idMedico;
	private ArrayList<String> dniPacientes;

	public Medico(String idMedico, ArrayList<String> dniPacientes) {
		super();
		this.idMedico = idMedico;
		this.dniPacientes = dniPacientes;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(String idMedico) {
		this.idMedico = idMedico;
	}

	public ArrayList<String> getDniPacientes() {
		return dniPacientes;
	}

	public void setDniPacientes(ArrayList<String> dniPacientes) {
		this.dniPacientes = dniPacientes;
	}

}