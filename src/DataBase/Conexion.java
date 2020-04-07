package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.CuidadorNew;
import model.PacienteNew;
import model.PersonaNew;

public class Conexion {
	String BBDDName;
	Connection conexion = null;
	Statement stmt = null;

//	public Conexion(String path) {
//		BBDDName = path;
//	}
	public Conexion() {
		BBDDName = "SmartGrand.db";
	}

	public boolean sentenciaSQL(String sql) {
		try {
			Class.forName("org.sqlite.JDBC");// creamos la conexion
			conexion = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);// abrir un fichero
			stmt = conexion.createStatement();// creamos una sentencia
			stmt.executeUpdate(sql);// lanza sentencias que no devuelven nada
			stmt.close();// cerrar la base de datos una ves finalizados
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	// revisar el id para que se auto incremente YA SEA POR JAVA O POR SQL y
	// investigar si llevas comillas el tipo DATE
	public void istPersona(Conexion conexion2, String nombre, String apellido, String usuario, String password,
			String dni, String fecha) {
		String insert = "INSERT INTO Personas(nombre, apellido, usuario, password, dni, fecha) " + "VALUES('" + nombre
				+ "', '" + apellido + "', '" + usuario + "', '" + password + "', '" + dni + "', " + fecha + ");";
		conexion2.sentenciaSQL(insert);
	}

	public void istMedico(Conexion conexion2, int id_med, String especialidad, int numColegiado, int id_cuidador) {
		String istMedico = "INSERT INTO Medico(id_med, especialidad, numColegiado, id_cuidador) " + "VALUES(" + id_med
				+ ", '" + especialidad + "', " + numColegiado + ", " + id_cuidador + ");";
		conexion2.sentenciaSQL(istMedico);
	}

	public void istPaciente(Conexion conexion2, int id_pac, String localidad, int numSegSocial, int id_cuidador) {
		String istPaciente = "INSERT INTO Paciente(id_pac, localidad, numSegSocial, id_cuidador) " + "VALUES(" + id_pac
				+ ", '" + localidad + "', " + numSegSocial + ", " + id_cuidador + ");";
		conexion2.sentenciaSQL(istPaciente);
	}

	public void istCuidador(Conexion conexion2, int id_cui, String especialidad) {
		String istCuidador = "INSERT INTO Cuidador(id_cui, especialidad) " + "VALUES(" + id_cui + ", '" + especialidad
				+ "');";
		conexion2.sentenciaSQL(istCuidador);
	}

	// creamos todas las tablas
	public void crearDb(Conexion conexion2) {
		String tablaMedico = "CREATE TABLE IF NOT EXISTS Medico(" + "id_med INTEGER PRIMARY KEY NOT NULL, "
				+ "especialidad TEXT not NULL, " + "numColegiado INTEGER not NULL," + "id_cuidador INTEGER NOT NULL,"
				+ "FOREIGN key (id_med) REFERENCES Personas(id_per),"
				+ "FOREIGN key (id_cuidador) REFERENCES Cuidador(id_cui)" + ");";
		conexion2.sentenciaSQL(tablaMedico);
		String tablaPaciente = "CREATE TABLE IF NOT EXISTS Paciente(" + "id_pac INTEGER PRIMARY KEY NOT NULL,"
				+ "localidad TEXT not NULL," + "numSegSocial INTEGER not NULL," + "id_cuidador INTEGER NOT NULL,"
				+ "FOREIGN key (id_pac) REFERENCES Personas(id_per),"
				+ "FOREIGN key (id_cuidador) REFERENCES Cuidador(id_cui)" + ");";
		conexion2.sentenciaSQL(tablaPaciente);
		String tablaCuidador = "CREATE TABLE IF NOT EXISTS Cuidador(" + "id_cui INTEGER PRIMARY KEY NOT NULL,"
				+ "especialidad TEXT not NULL," + "FOREIGN key (id_cui) REFERENCES Personas(id_per)" + ");";
		conexion2.sentenciaSQL(tablaCuidador);
		String tablaPersona = "CREATE TABLE IF NOT EXISTS Personas("
				+ "id_per INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "nombre TEXT not NULL, "
				+ "apellido TEXT not NULL, " + "usuario TEXT not NULL, " + "password TEXT not null, "
				+ "dni TEXT NOT NULL, " + "fecha TEXT NOT NULL " + ");";

		conexion2.sentenciaSQL(tablaPersona);

//		Date fecha = new Date(0);
//		istPersona(this, "sebas", "quinayas", "PP", "123", "123", fecha);
//		istCuidador(conexion2, 1, "corazon");
	}

	// PARA CONSULTAR LA BASE DE DATOS QUE DEVUELVE COSAS

	public PersonaNew consultaPersona(String dni) {
		int id_per = 0;
		String nombre = null;
		String apellido = null;
		String usuario = null;
		String password = null;
		String dni2 = null;
		String fecha = null;
		PersonaNew per = new PersonaNew();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Personas where dni= '" + dni + "';");// conjunto de resultados

			while (rs.next()) {
				// aqui colocar un objeto
				id_per = rs.getInt("id_per");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				usuario = rs.getString("usuario");
				password = rs.getString("password");
				dni2 = rs.getString("dni");
				fecha = rs.getString("fecha");
			}
			PersonaNew persona = new PersonaNew(nombre, apellido, dni2, usuario, password, fecha);
			per = persona;
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		return per;
	}
		
}
