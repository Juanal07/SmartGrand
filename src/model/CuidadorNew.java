package model;

public class CuidadorNew {
	private int id_cui;
	private String especialidad;

	public CuidadorNew(int id_cui, String especialidad) {
		super();
		this.id_cui = id_cui;
		this.especialidad = especialidad;
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
