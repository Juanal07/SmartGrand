package model;

public class MedicoNew {

	private int id_med, numColegiado;
	private String especialidad;
	private boolean verificado;

	public MedicoNew() {
		super();
	}

	public MedicoNew(int id_med, int numColegiado, String especialidad, boolean verificado) {
		super();
		this.id_med = id_med;
		this.numColegiado = numColegiado;
		this.especialidad = especialidad;
		this.verificado = verificado;
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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

}
