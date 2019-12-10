package Model;

import java.util.ArrayList;

public class Cuidador {

	private String idCuidador;
	private ArrayList<String> dniPacientes;

	public Cuidador(String idCuidador, ArrayList<String> dniPacientes) {
		super();
		this.idCuidador = idCuidador;
		this.dniPacientes = dniPacientes;
	}

	public String getIdCuidador() {
		return idCuidador;
	}

	public void setIdCuidador(String idCuidador) {
		this.idCuidador = idCuidador;
	}

	public ArrayList<String> getDniPacientes() {
		return dniPacientes;
	}

	public void setDniPacientes(ArrayList<String> dniPacientes) {
		this.dniPacientes = dniPacientes;
	}

}