package model;

public class PersonaNew {
	// el id_per no se pide porque es autoIncrement

	private String nombre, apellido, dni, usuario, psw, fecha, tipo;
	private int id_per;

	// para insertar una persona en la tabla
	public PersonaNew(String nombre, String apellido, String dni, String usuario, String psw, String fecha,
			String tipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuario = usuario;
		this.psw = psw;
		this.fecha = fecha;
		this.tipo = tipo;
	}

	public PersonaNew(String nombre, String apellido, String dni, String usuario, String psw, String fecha,
			String tipo, int id_per) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuario = usuario;
		this.psw = psw;
		this.fecha = fecha;
		this.tipo = tipo;
		this.id_per = id_per;
	}

	public PersonaNew() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_per() {
		return id_per;
	}

	public void setId_per(int id_per) {
		this.id_per = id_per;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getpsw() {
		return psw;
	}

	public void setpsw(String psw) {
		this.psw = psw;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
