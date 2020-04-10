package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.ObservableList;
import model.CuidadorNew;
import model.MedicoNew;
import model.PacienteNew;
import model.Persona;
import model.PersonaNew;
import model.Tickets;
import model.TicketsNew;

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
			String dni, String fecha, String tipo) {
		String insert = "INSERT INTO Personas(nombre, apellido, usuario, password, dni, fecha) VALUES('" + nombre
				+ "', '" + apellido + "', '" + usuario + "', '" + password + "', '" + dni + "', '" + fecha + "', '" + tipo + "');";
		conexion2.sentenciaSQL(insert);
	}

	public void istMedico(Conexion conexion2, int id_med, String especialidad, int numColegiado, boolean verificado) {
		String istMedico = "INSERT INTO Medico(id_med, especialidad, numColegiado, id_cuidador, id_paciente) VALUES('"
				+ id_med + "', '" + especialidad + "', '" + numColegiado + "', '" + verificado +"');";
		conexion2.sentenciaSQL(istMedico);
	}

	public void istPaciente(Conexion conexion2, int id_pac, String localidad, int numSegSocial, int id_cuidador, int id_medico) {
		String istPaciente = "INSERT INTO Paciente(id_pac, localidad, numSegSocial, id_cuidador) VALUES('" + id_pac
				+ "', '" + localidad + "', '" + numSegSocial + "', '" + id_cuidador + "', '" + id_medico + "');";
		conexion2.sentenciaSQL(istPaciente);
	}

	public void istCuidador(Conexion conexion2, int id_cui, String especialidad, int  id_medico) {
		String istCuidador = "INSERT INTO Cuidador(id_cui, especialidad) VALUES('" + id_cui + "', '" + especialidad
				+ "', '" + id_medico + "');";
		conexion2.sentenciaSQL(istCuidador);
	}

	public void istTicket(Conexion conexion2, String textoPaciente, Date fechaPaciente, String textoMedico,
			Date fechaMedico, int id_medico, int id_paciente) {
		String istTicket = "INSERT INTO Ticket(Texto_Paciente, Fecha_Paciente, Texto_Medico Fecha_Medico, id_medico, id_paciente) VALUES('"
				+ textoPaciente + "', '" + fechaPaciente + "', '" + textoMedico + "', '" + fechaMedico + "', '"
				+ id_medico + "', '" + id_paciente + "');";
		conexion2.sentenciaSQL(istTicket);
	}

	public void istSensor(Conexion conexion2, String Ubicacion, String Tipo, int Dato, Date Fecha, int id_paciente) {
		String istSensor = "INSERT INTO Sensor(Ubicacion, Tipo, Dato, Fecha, id_paciente) VALUES('" + Ubicacion + "', '"
				+ Tipo + "', '" + Fecha + "', '" + id_paciente + "');";
		conexion2.sentenciaSQL(istSensor);
	}

	// creamos todas las tablas
	public void crearDb(Conexion conexion2) {
		String tablaMedico = "CREATE TABLE IF NOT EXISTS Medico(" 
				+ "id_med INTEGER PRIMARY KEY NOT NULL, "
				+ "especialidad TEXT not NULL, " 
				+ "numColegiado INTEGER not NULL,"
				+ "verificado BOOLEAN," 
				+ "FOREIGN key (id_med) REFERENCES Personas(id_per)" 
				+ ");";
		conexion2.sentenciaSQL(tablaMedico);

		String tablaPaciente = "CREATE TABLE IF NOT EXISTS Paciente(" 
				+ "id_pac INTEGER PRIMARY KEY NOT NULL,"
				+ "localidad TEXT not NULL," 
				+ "numSegSocial INTEGER not NULL," 
				+ "id_cuidador INTEGER,"
				+ "id_medico INTEGER,"
				+ "FOREIGN key (id_pac) REFERENCES Personas(id_per),"
				+ "FOREIGN key (id_cuidador) REFERENCES Cuidador(id_cui)," 
				+ "FOREIGN key (id_medico) REFERENCES Medico(id_med)"
				+ ");";
		conexion2.sentenciaSQL(tablaPaciente);

		String tablaCuidador = "CREATE TABLE IF NOT EXISTS Cuidador(" 
				+ "id_cui INTEGER PRIMARY KEY NOT NULL,"
				+ "especialidad TEXT not NULL," 
				+ "id_medico INTEGER,"
				+ "FOREIGN key (id_cui) REFERENCES Personas(id_per),"
				+ "FOREIGN key (id_medico) REFERENCES Medico(id_med)"
				+ ");";
		conexion2.sentenciaSQL(tablaCuidador);

		String tablaPersona = "CREATE TABLE IF NOT EXISTS Personas("
				+ "id_per INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " 
				+ "nombre TEXT not NULL, "
				+ "apellido TEXT not NULL, " 
				+ "usuario TEXT not NULL, " 
				+ "password TEXT not null, "
				+ "dni TEXT NOT NULL, " 
				+ "fecha TEXT NOT NULL, " 
				+ "tipo TEXT NOT NULL"
				+ ");";
		conexion2.sentenciaSQL(tablaPersona);

		String tablaTicket = "CREATE TABLE IF NOT EXISTS Ticket("
				+ "id_tic INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " 
				+ "Texto_Paciente TEXT not NULL, "
				+ "Fecha_Paciente Date not NULL, " 
				+ "Texto_Medico TEXT not NULL, " 
				+ "Fecha_Medico Date not null, "
				+ "id_medico INTEGER," 
				+ "id_paciente INTEGER," 
				+ "FOREIGN KEY (id_medico) REFERENCES Medico(id_med),"
				+ "FOREIGN KEY (id_paciente) REFERENCES Paciente(id_pac)" + ");";
		conexion2.sentenciaSQL(tablaTicket);

		String tablaSensor = "CREATE TABLE IF NOT EXISTS Sensor("
				+ "id_sen INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " 
				+ "Ubicacion TEXT not NULL, "
				+ "Tipo Date not NULL, " 
				+ "Dato INTEGER not NULL, " 
				+ "Fecha Date not null, "
				+ "id_paciente INTEGER NOT NULL," 
				+ "FOREIGN KEY (id_paciente) REFERENCES Paciente(id_pac)" + ");";
		conexion2.sentenciaSQL(tablaSensor);

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
			ResultSet rs = stmt.executeQuery("SELECT * FROM Personas where dni= '" + dni + "';");// conjunto de
																									// resultados

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
			PersonaNew persona = new PersonaNew(id_per, nombre, apellido, dni2, usuario, password, fecha);
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

	public PacienteNew consultaPaciente(int id_per) {
		int id_pac = 0, numSegSocial = 0, id_cuidador = 0;
		String localidad = null;
		PacienteNew p = new PacienteNew();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Paciente where id_pac = " + id_per + ";");// conjunto de
																										// resultados

			while (rs.next()) {
				// aqui colocar un objeto
				id_pac = rs.getInt("id_pac");
				numSegSocial = rs.getInt("numSegSocial");
				id_cuidador = rs.getInt("id_cuidador");
				localidad = rs.getString("localidad");
			}
			PacienteNew paciente = new PacienteNew(id_pac, localidad, numSegSocial, id_cuidador);
			p = paciente;
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		return p;
	}

	public MedicoNew consultaMedico(int id_per) {
		int id_med = 0, numColegiado = 0;
		String especialidad = null;
		boolean verificado = false;
		MedicoNew medicoNew = new MedicoNew();
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Medico where id_paciente = " + id_per + ";");// conjunto de
																											// resultados
			while (rs.next()) {
				// aqui colocar un objeto
				id_med = rs.getInt("id_med");
				especialidad = rs.getString("especialidad");
				numColegiado = rs.getInt("numColegiado");
				verificado = rs.getBoolean("verificado");
				
			}
			MedicoNew new1 = new MedicoNew(id_med, numColegiado, especialidad, verificado);
			medicoNew = new1;
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		return medicoNew;
	}

	public void leerTickets(ObservableList<TicketsNew> ticketsObservableList, PersonaNew p) {
		int id_tic, id_medico, id_paciente;
		String texto_Paciente, texto_Medico;
		Date fecha_Paciente, fecha_Medico;

		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + BBDDName);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Ticket where id_paciente = " + p.getId_per() + ";");// conjunto
																												// de
			// resultados

			while (rs.next()) {
				// aqui colocar un objeto
				id_tic = rs.getInt("id_tic");
				id_medico = rs.getInt("id_medico");
				id_paciente = rs.getInt("id_paciente");
				texto_Medico = rs.getString("texto_Medico");
				texto_Paciente = rs.getString("texto_Paciente");
				fecha_Paciente = rs.getDate("Fecha_Paciente");
				fecha_Medico = rs.getDate("Fecha_Medico");
				TicketsNew ticketsNew = new TicketsNew(id_tic, id_medico, id_paciente, texto_Paciente, texto_Medico,
						fecha_Paciente, fecha_Medico);
				ticketsObservableList.add(ticketsNew);
			}
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
	}
}
