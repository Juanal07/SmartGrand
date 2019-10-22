package Model;

public class Paciente {
	private String nombre;
	private String apellidos;
	private String DNI;
	private String localidad;
	private String direccion;
	private String foto;
	private String usuario;
	private String password;

	public Paciente() {

	}

	public Paciente(String nombre, String apellidos, String dNI, String localidad, String direccion, String foto,
			String usuario, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.DNI = dNI;
		this.localidad = localidad;
		this.direccion = direccion;
		this.foto = foto;
		this.usuario = usuario;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}