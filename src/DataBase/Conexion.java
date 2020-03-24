package DataBase;

import java.sql.Connection;
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
	// creamos todas las tablas
	public void crearDb(Conexion conexion2) {
		String tablaMedico = "CREATE TABLE IF NOT EXISTS Medico(" + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + 
				"especialidad TEXT not NULL, " + 
				"numColegiado INTEGER not NULL" +
				");";
		conexion2.sentenciaSQL(tablaMedico);
		String tablaPaciente = "CREATE TABLE IF NOT EXISTS Paciente(" + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				"localidad TEXT not NULL," + 
				"numSegSocial INTEGER not NULL," + 
				"FOREIGN key(id) REFERENCES Medico(id)"+
				");";
		conexion2.sentenciaSQL(tablaPaciente);
		String tablaCuidador = "CREATE TABLE IF NOT EXISTS Cuidador(" + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"especialidad TEXT not NULL" + 
				");";
		conexion2.sentenciaSQL(tablaCuidador);	
		String tablaPersona  = "CREATE TABLE IF NOT EXISTS Personas(" + 
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"nombre TEXT not NULL, " + 
				"apellido TEXT not NULL, " + 
				"usuario TEXT not NULL, " + 
				"password TEXT not null, " + 
				"dni INTEGER NOT NULL, " + 
				"fecha DATE NOT NULL, " + 
				"FOREIGN key(id) REFERENCES Medico(id), " + 
				"FOREIGN key (id) REFERENCES Cuidador(id), " + 
				"FOREIGN key (id) REFERENCES Paciente(id), " + 
				"FOREIGN key (id) REFERENCES SuperUsuario(id) " + 
				");";
		conexion2.sentenciaSQL(tablaPersona);
	}
}
