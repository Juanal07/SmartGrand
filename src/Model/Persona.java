package Model;

public class Persona {
	private String usuario;
	private String password;
	private String nombre;
	private String apellido;
	private String tipoUsuario;

	public Persona(String usuario, String password, String nombre, String apellido, String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoUsuario = tipoUsuario;
	}

	public Persona() {
		// TODO Auto-generated constructor stub
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
