package Model;

public class Cuidador extends Persona{

	private String direccion;
	private String correoElectronico;
	private int telefono;
	
	
	public Cuidador(String usuario, String password, String nombre, String apellido, String tipoUsuario,
			String direccion, String correoElectronico, int telefono) {
		super(usuario, password, nombre, apellido, tipoUsuario);
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
}