package model;

public class PacienteNew {
	private int id_pac, numSegSocial, id_cuidador, id_medico;
	private String localidad;

	

	public PacienteNew(int id_pac, int numSegSocial, int id_cuidador, int id_medico, String localidad) {
		super();
		this.id_pac = id_pac;
		this.numSegSocial = numSegSocial;
		this.id_cuidador = id_cuidador;
		this.id_medico = id_medico;
		this.localidad = localidad;
	}

	public PacienteNew() {
		// TODO Auto-generated constructor stub
	}

	public int getId_medico() {
		return id_medico;
	}

	public void setId_medico(int id_medico) {
		this.id_medico = id_medico;
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
