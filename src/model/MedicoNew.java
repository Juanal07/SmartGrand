package model;

public class MedicoNew {

	private int id_med, numColegiado, id_cuidador, id_paciente;
	private String especialidad;

	public MedicoNew() {
		super();
	}

	public MedicoNew(int id_med, int numColegiado, int id_cuidador, int id_paciente, String especialidad) {
		super();
		this.id_med = id_med;
		this.numColegiado = numColegiado;
		this.id_cuidador = id_cuidador;
		this.id_paciente = id_paciente;
		this.especialidad = especialidad;
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

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
