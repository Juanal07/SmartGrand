package model;

public class PacienteNew {
	private int id_pac, numSegSocial, id_cuidador;
	private String localidad;

	public PacienteNew(int id_pac, String localidad, int numSegSocial, int id_cuidador) {
		super();
		this.id_pac = id_pac;
		this.localidad = localidad;
		this.numSegSocial = numSegSocial;
		this.id_cuidador = id_cuidador;
	}

	public PacienteNew() {
		// TODO Auto-generated constructor stub
	}

	public int getId_pac() {
		return id_pac;
	}

	public void setId_pac(int id_pac) {
		this.id_pac = id_pac;
	}

	public int getNumSegSocial() {
		return numSegSocial;
	}

	public void setNumSegSocial(int numSegSocial) {
		this.numSegSocial = numSegSocial;
	}

	public int getId_cuidador() {
		return id_cuidador;
	}

	public void setId_cuidador(int id_cuidador) {
		this.id_cuidador = id_cuidador;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
