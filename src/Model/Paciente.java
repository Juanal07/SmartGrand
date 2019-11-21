package Model;

public class Paciente extends Persona {
	private String localidad;
	private String direccion;

	public Paciente(String usuario, String password, String nombre, String apellido, String tipoUsuario, String dNI,
			String localidad, String direccion, String dni) {
		super(usuario, password, nombre, apellido, tipoUsuario, dni);
		this.localidad = localidad;
		this.direccion = direccion;
		
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}