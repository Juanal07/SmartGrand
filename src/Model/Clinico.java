package Model;

public class Clinico extends Persona {

	private String correoElectronico;
	private String dni;
	private String especialidad;
	private String fechaNacimiento;
	private String nacionalidad;
	private String centroMedico;
	private String numeroLicencia;

	public Clinico(String usuario, String password, String nombre, String apellido, String tipoUsuario,
			String correoElectronico, String dni, String especialidad, String fechaNacimiento, String nacionalidad,
			String centroMedico, String numeroLicencia) {
		super(usuario, password, nombre, apellido, tipoUsuario, dni);
		this.correoElectronico = correoElectronico;
		this.dni = dni;
		this.especialidad = especialidad;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.centroMedico = centroMedico;
		this.numeroLicencia = numeroLicencia;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCentroMedico() {
		return centroMedico;
	}

	public void setCentroMedico(String centroMedico) {
		this.centroMedico = centroMedico;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

}
