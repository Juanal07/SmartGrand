package model;

public class MedicoNew {
	private int id_med, numColegiado, id_cuidador;
	private String especialidad;

	public MedicoNew(int id_med, String especialidad, int numColegiado, int id_cuidador) {
		this.id_med = id_med;
		this.especialidad = especialidad;
		this.numColegiado = numColegiado;
		this.id_cuidador = id_cuidador;
	}

	public int getId_med() {
		return id_med;
	}

	public void setId_med(int id_med) {
		this.id_med = id_med;
	}

	public int getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(int numColegiado) {
		this.numColegiado = numColegiado;
	}

	public int getId_cuidador() {
		return id_cuidador;
	}

	public void setId_cuidador(int id_cuidador) {
		this.id_cuidador = id_cuidador;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
