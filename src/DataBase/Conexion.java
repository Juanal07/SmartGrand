package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexion {
	String BBDDName;
	Connection conexion = null;
	Statement stmt = null;

	public Conexion(String path) {
		BBDDName = path;
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
	
	// revisar el id para que se auto incremente YA SEA POR JAVA O POR SQL y investigar si llevas comillas el tipo DATE
	public void istPersona(Conexion conexion2, String nombre, String apellido, String usuario, String password, String dni, Date fecha) {
		String insert = "INSERT INTO Personas(nombre, apellido, usuario, password, dni, fecha) "
				+ 		"VALUES('" + nombre + "', '" + apellido + "', '" + usuario + "', '" + password + "', '" + dni + "', " + fecha + ");";
		conexion2.sentenciaSQL(insert);
	}
	
	public void istMedico(Conexion conexion2, String especialidad, int numColegiado ) {
		String istMedico = "INSERT INTO Medico(especialidad, numColegiado) VALUES('" + especialidad + "', " + numColegiado + ");";
		conexion2.sentenciaSQL(istMedico);
	}
	
	public void istPaciente(Conexion conexion2, String localidad, int numSegSocial) {
		String istPaciente = "INSERT INTO Medico(localidad, numSegSocial) VALUES('" + localidad + "', " + numSegSocial + ");";
		conexion2.sentenciaSQL(istPaciente);
	}
	
	public void istCuidador(Conexion conexion2,String especialidad) {
		String istCuidador = "INSERT INTO Cuidador(especialidad) VALUES('" + especialidad + "');";
		conexion2.sentenciaSQL(istCuidador);
	}
	
	// creamos todas las tablas
	public void crearDb(Conexion conexion2) {
		String tablaMedico = "CREATE TABLE IF NOT EXISTS Medico(" + 
				"id_med INTEGER PRIMARY KEY NOT NULL, " + 
				"especialidad TEXT not NULL, " + 
				"numColegiado INTEGER not NULL" +
				"FOREIGN key (id) REFERENCES Personas(id)"+
				"FOREIGN key (id_cui) REFERENCES Cuidador(id)"+
				");";
		conexion2.sentenciaSQL(tablaMedico);
		String tablaPaciente = "CREATE TABLE IF NOT EXISTS Paciente(" + 
				"id_pac INTEGER PRIMARY KEY NOT NULL," + 
				"localidad TEXT not NULL," + 
				"numSegSocial INTEGER not NULL," + 
				"FOREIGN key (id) REFERENCES Personas(id)"+
				"FOREIGN key (id_cui) REFERENCES Cuidador(id)"+
				");";
		conexion2.sentenciaSQL(tablaPaciente);
		String tablaCuidador = "CREATE TABLE IF NOT EXISTS Cuidador(" + 
				"id_cui INTEGER PRIMARY KEY NOT NULL," + 
				"especialidad TEXT not NULL" + 
				"FOREIGN key (id) REFERENCES Personas(id)"+
				");";
		conexion2.sentenciaSQL(tablaCuidador);	
		String tablaPersona  = "CREATE TABLE IF NOT EXISTS Personas(" + 
				"id_per INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + 
				"nombre TEXT not NULL, " + 
				"apellido TEXT not NULL, " + 
				"usuario TEXT not NULL, " + 
				"password TEXT not null, " + 
				"dni INTEGER NOT NULL, " + 
				"fecha DATE NOT NULL, " + 
				");";
		conexion2.sentenciaSQL(tablaPersona);
	}
}
