package model;

public class CuidadorNew {
	private int id_cui, id_medico;
	private String especialidad;

	public CuidadorNew(int id_cui, int id_medico, String especialidad) {
		super();
		this.id_cui = id_cui;
		this.id_medico = id_medico;
		this.especialidad = especialidad;
	}

	public int getId_medico() {
		return id_medico;
	}

	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
	}

	public int getId_cui() {
		return id_cui;
	}

	public void setId_cui(int id_cui) {
		this.id_cui = id_cui;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
