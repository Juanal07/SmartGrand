package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import model.MedicoNew;
import model.PacienteNew;
import model.PersonaNew;
import model.Sensor1New;
import model.TicketsNew;

public class Conexion {
	private String BBDDName;
	private Connection conexion = null;
	private Statement stmt = null;
	private static final String USER = "pr_smartgrant";
	private static final String PASS = "3SmartGrant";

	public Conexion() {
		BBDDName = "pr_smartgrant";
	}

	public boolean sentenciaSQL(String sql) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			stmt = conexion.createStatement();// creamos una sentencia
			stmt.executeUpdate(sql);// lanza sentencias que no devuelven nada
			stmt.close();// cerrar la base de datos una ves finalizados
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error sentencia sql.");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	// revisar el id para que se auto incremente YA SEA POR JAVA O POR SQL y
	// investigar si llevas comillas el tipo DATE
	public void istPersona(Conexion conexion, String nombre, String apellido, String usuario, String psw, String dni,
			String fecha, String tipo) {
		String insert = "INSERT INTO Personas(nombre, apellido, usuario, psw, dni, fecha, tipo) VALUES('" + nombre
				+ "', '" + apellido + "', '" + usuario + "', '" + psw + "', '" + dni + "', '" + fecha + "', '" + tipo
				+ "');";
		conexion.sentenciaSQL(insert);
	}

	public void istMedico(Conexion conexion2, int id_med, String especialidad, int numColegiado, boolean verificado) {
		String istMedico = "INSERT INTO Medico(id_med, especialidad, numColegiado, verificado) VALUES('" + id_med
				+ "', '" + especialidad + "', '" + numColegiado + "', " + verificado + ");";
		conexion2.sentenciaSQL(istMedico);
	}

	public void istPaciente(Conexion conexion2, int id_pac, String localidad, int numSegSocial,
			int id_medico) {
		String istPaciente = "INSERT INTO Paciente(id_pac, localidad, numSegSocial, id_cuidador, id_medico) VALUES('"
				+ id_pac + "', '" + localidad + "', '" + numSegSocial + "',  null , '" + id_medico
				+ "');";
		conexion2.sentenciaSQL(istPaciente);
	}

	public void istCuidador(Conexion conexion2, int id_cui, String especialidad, int id_medico) {
		String istCuidador = "INSERT INTO Cuidador(id_cui, especialidad, id_medico) VALUES('" + id_cui + "', '"
				+ especialidad + "', '" + id_medico + "');";
		conexion2.sentenciaSQL(istCuidador);
	}

	public void istTicketPaciente(Conexion conexion2, String textoPaciente, String fechaPaciente, int id_medico,
			int id_paciente) {
		System.out.println(" enviando ticket");
		String istTicket = "INSERT INTO Ticket(Texto_Paciente, Fecha_Paciente, id_medico, id_paciente)" + " VALUES('"
				+ textoPaciente + "', '" + fechaPaciente + "', " + id_medico + ", " + id_paciente + ");";
		conexion2.sentenciaSQL(istTicket);
	}

	public void istSensor(Conexion conexion2, String Ubicacion, String Tipo, int Dato, Date Fecha, int id_paciente) {
		String istSensor = "INSERT INTO Sensor(Ubicacion, Tipo, Dato, Fecha, id_paciente) VALUES('" + Ubicacion + "', '"
				+ Tipo + "', '" + Fecha + "', '" + id_paciente + "');";
		conexion2.sentenciaSQL(istSensor);
	}

	// creamos todas las tablas
	public void crearDb(Conexion conexion2) {
		String tablaPersona = "CREATE TABLE IF NOT EXISTS Personas(\r\n"
				+ "id_per INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,  \r\n" + "nombre TEXT not NULL, \r\n"
				+ "apellido TEXT not NULL,  \r\n" + "usuario TEXT not NULL,  \r\n" + "psw TEXT not null, \r\n"
				+ "dni TEXT NOT NULL,  \r\n" + "fecha TEXT NOT NULL,  \r\n" + "tipo TEXT NOT NULL \r\n" + ");";
		conexion2.sentenciaSQL(tablaPersona);

		String tablaMedico = "CREATE TABLE IF NOT EXISTS Medico( \r\n" + "id_med INTEGER PRIMARY KEY NOT NULL, \r\n"
				+ "especialidad TEXT not NULL, \r\n" + "numColegiado INTEGER not NULL,\r\n" + "verificado BOOLEAN,\r\n"
				+ "FOREIGN key (id_med) REFERENCES Personas (id_per) \r\n" + ");";
		conexion2.sentenciaSQL(tablaMedico);

		String tablaCuidador = "CREATE TABLE IF NOT EXISTS Cuidador( \r\n" + "id_cui INTEGER PRIMARY KEY NOT NULL,\r\n"
				+ "especialidad TEXT not NULL, \r\n" + "id_medico INTEGER,\r\n"
				+ "FOREIGN key (id_cui) REFERENCES Personas (id_per),\r\n"
				+ "FOREIGN key (id_medico) REFERENCES Medico (id_med) \r\n" + ");";
		conexion2.sentenciaSQL(tablaCuidador);

		String tablaPaciente = "CREATE TABLE IF NOT EXISTS Paciente( \r\n" + "id_pac INTEGER PRIMARY KEY NOT NULL,\r\n"
				+ "localidad TEXT not NULL, \r\n" + "numSegSocial INTEGER not NULL, \r\n" + "id_cuidador INTEGER,\r\n"
				+ "id_medico INTEGER, \r\n" + "FOREIGN key (id_pac) REFERENCES Personas (id_per),\r\n"
				+ "FOREIGN key (id_cuidador) REFERENCES Cuidador (id_cui),\r\n"
				+ "FOREIGN key (id_medico) REFERENCES Medico (id_med) \r\n" + ");";
		conexion2.sentenciaSQL(tablaPaciente);

		String tablaTicket = "CREATE TABLE IF NOT EXISTS Ticket(\r\n"
				+ "id_tic INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,  \r\n" + "Texto_Paciente TEXT not NULL, \r\n"
				+ "Fecha_Paciente TEXT not NULL,  \r\n" + "Texto_Medico TEXT,  \r\n" + "Fecha_Medico TEXT, \r\n"
				+ "id_medico INTEGER, \r\n" + "id_paciente INTEGER, \r\n"
				+ "FOREIGN KEY (id_medico) REFERENCES Medico (id_med),\r\n"
				+ "FOREIGN KEY (id_paciente) REFERENCES Paciente (id_pac) \r\n" + ");";
		conexion2.sentenciaSQL(tablaTicket);

		String tablaSensor = "CREATE TABLE IF NOT EXISTS Sensor(\r\n"
				+ "id_sen INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,  \r\n" + "Ubicacion TEXT not NULL, \r\n"
				+ "Tipo TEXT not NULL,  \r\n" + "Dato INTEGER not NULL,  \r\n" + "Fecha DATE not null, \r\n"
				+ "id_paciente INTEGER NOT NULL, \r\n" + "FOREIGN KEY (id_paciente) REFERENCES Paciente(id_pac)  \r\n"
				+ ");";
		conexion2.sentenciaSQL(tablaSensor);

	}

	// PARA CONSULTAR LA BASE DE DATOS QUE DEVUELVE COSAS

	public <T> PersonaNew consultaPersona(String whereCond, T var) {
		int id_per = 0;
		String nombre = null;
		String apellido = null;
		String usuario = null;
		String psw = null;
		String dni2 = null;
		String fecha = null;
		String tipo = null;
		PersonaNew per = new PersonaNew();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = null;
			// Reconoce si es de tipo string o int
			if (whereCond.getClass().equals(Integer.class)) {
				rs = stmt.executeQuery("SELECT * FROM Personas where " + whereCond + " = " + var + ";");
			} else if (whereCond.getClass().equals(String.class)) {
				rs = stmt.executeQuery("SELECT * FROM Personas where " + whereCond + " = '" + var + "';");
			}
			// ResultSet rs = stmt.executeQuery("SELECT * FROM Personas where " + whereCond
			// + " = '" + dni + "';");
			while (rs.next()) {
				// aqui colocar un objeto
				id_per = rs.getInt("id_per");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				usuario = rs.getString("usuario");
				psw = rs.getString("psw");
				dni2 = rs.getString("dni");
				fecha = rs.getString("fecha");
				tipo = rs.getString("tipo");
			}
			PersonaNew persona = new PersonaNew(nombre, apellido, dni2, usuario, psw, fecha, tipo, id_per);
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

	public PacienteNew consultaPaciente(String where, int id_per) {
		int id_pac = 0, numSegSocial = 0, id_cuidador = 0, id_medico = 0;
		String localidad = null;

		PacienteNew p = new PacienteNew();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Paciente where " + where + " = " + id_per + ";");// conjunto
																												// de
			// resultados
			while (rs.next()) {
				// aqui colocar un objeto
				id_pac = rs.getInt("id_pac");
				numSegSocial = rs.getInt("numSegSocial");
				id_cuidador = rs.getInt("id_cuidador");
				id_medico = rs.getInt("id_medico");
				localidad = rs.getString("localidad");
			}
			PacienteNew paciente = new PacienteNew(id_pac, numSegSocial, id_cuidador, id_medico, localidad);
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
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
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

	public void leerTickets(ObservableList<TicketsNew> ticketsObservableList, String pWhere, int id) {
		int id_tic, id_medico, id_paciente;
		String texto_Paciente, texto_Medico;
		String fecha_Paciente, fecha_Medico;
		// Date fecha_Medico;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			// todos los tickets que coinsidan con su id y texto medico vacio
			ResultSet rs = stmt.executeQuery("SELECT * FROM Ticket where " + pWhere + " = " + id + " AND Texto_Medico is null;");

			while (rs.next()) {
				// aqui colocar un objeto
				id_tic = rs.getInt("id_tic");
				texto_Paciente = rs.getString("Texto_Paciente");
				fecha_Paciente = rs.getString("Fecha_Paciente");// getDate("Fecha_Paciente");
				texto_Medico = rs.getString("Texto_Medico");
				fecha_Medico = rs.getString("Fecha_Medico");
				id_medico = rs.getInt("id_medico");
				id_paciente = rs.getInt("id_paciente");
				TicketsNew new1 = new TicketsNew(id_tic, id_medico, id_paciente, texto_Paciente, texto_Medico,
						fecha_Paciente, fecha_Medico);
				ticketsObservableList.add(new1);
			}
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println("ERROR: ticket conexion");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
	}

	public void consultaPacientes(int id_per, ObservableList<PacienteNew> pacientesObservableList2) {
		int id_pac = 0, numSegSocial = 0, id_cuidador = 0, id_medico = 0;
		String localidad = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();// devuelve todos lo pacientes del medico
			ResultSet rs = stmt.executeQuery("SELECT * FROM Paciente where id_medico = " + id_per + ";");

			while (rs.next()) {
				// aqui colocar un objeto
				id_pac = rs.getInt("id_pac");
				numSegSocial = rs.getInt("numSegSocial");
				id_cuidador = rs.getInt("id_cuidador");
				id_medico = rs.getInt("id_medico");
				localidad = rs.getString("localidad");
				pacientesObservableList2.add(new PacienteNew(id_pac, numSegSocial, id_cuidador, id_medico, localidad));
			}
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println("MTD consulta paciente.");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
	}

	public void respuestTicket(Conexion conexion, int idTic, String fechaMedico, String txtMedico) {
		// investigar si esta sentecia esta bien
		String update = "UPDATE Ticket " + "SET Texto_Medico = '" + txtMedico + "', " + "Fecha_Medico = '" + fechaMedico
				+ "' " + "WHERE id_tic = " + idTic + ";";
		conexion.sentenciaSQL(update);
	}

	public List<String> listaDnis() {

		String dni2 = null;
		List<String> dnis = new ArrayList<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT dni FROM Personas;");

			while (rs.next()) {
				dni2 = rs.getString("dni");
				dnis.add(dni2);
			}

			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

		return dnis;
	}

	public List<String> listaUsuarios() {

		String user = null;
		List<String> usuarios = new ArrayList<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT usuario FROM Personas;");

			while (rs.next()) {
				user = rs.getString("usuario");
				usuarios.add(user);
			}

			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

		return usuarios;
	}

	public List<String> listaMedicosNoVerificados() {

		int id_per, numColegiado;
		String nombre = null;
		String apellido = null;
		List<String> medicos = new ArrayList<String>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM Personas JOIN Medico ON Medico.id_med = Personas.id_per  WHERE medico.verificado = 'false';");

			while (rs.next()) {
				id_per = rs.getInt("id_per");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				numColegiado = rs.getInt("numColegiado");
				medicos.add(id_per + "-" + " " + nombre + " " + apellido + " " + numColegiado);
			}

			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

		return medicos;
	}

	public PersonaNew consultaPersonaUsuario(String user) {
		int id_per = 0;
		String nombre = null;
		String apellido = null;
		String usuario = null;
		String psw = null;
		String dni2 = null;
		String fecha = null;
		String tipo = null;
		PersonaNew per = new PersonaNew();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Personas where usuario= '" + user + "';");// conjunto de
																								// resultados
			while (rs.next()) {
				// aqui colocar un objeto
				id_per = rs.getInt("id_per");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				usuario = rs.getString("usuario");
				psw = rs.getString("psw");
				dni2 = rs.getString("dni");
				fecha = rs.getString("fecha");
				tipo = rs.getString("tipo");
			}
			PersonaNew persona = new PersonaNew(nombre, apellido, dni2, usuario, psw, fecha, tipo, id_per);
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

	public void eliminarPersona(String user) {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery("DELETE FROM Paciente WHERE id_pac = (SELECT id_per FROM Personas WHERE usuario ='"+user+"');");	
			rs = stmt.executeQuery("DELETE FROM Medico WHERE id_med = (SELECT id_per FROM Personas WHERE usuario ='"+user+"');");
			rs = stmt.executeQuery("DELETE FROM Cuidador WHERE id_cui = (SELECT id_per FROM Personas WHERE usuario ='"+user+"');");
			rs = stmt.executeQuery("DELETE FROM Ticket WHERE id_paciente = (SELECT id_per FROM Personas WHERE usuario ='"+user+"');");
			rs = stmt.executeQuery("DELETE FROM Personas WHERE id_per = (SELECT id_per FROM Personas WHERE usuario ='"+user+"');");
			
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println("entra aquiiiiiiiiiiii");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

	}

	public void verificarMedico(int id) {

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);

			stmt = conexion.createStatement();

			ResultSet rs = stmt.executeQuery("UPDATE medico SET verificado = 1 WHERE id_med = " + id);
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

	}

	public List<PersonaNew> listaPacientesCuidador(int id) {
		int id_per = 0;
		String nombre = null;
		String apellido = null;
		String usuario = null;
		String psw = null;
		String dni2 = null;
		String fecha = null;
		String tipo = null;

		List<PersonaNew> usuarios = new ArrayList<PersonaNew>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT \r\n" + "    personas.id_per,\r\n" + "    personas.nombre,\r\n"
					+ "    personas.apellido,\r\n" + "    personas.usuario,\r\n" + "    personas.psw,\r\n"
					+ "    personas.dni,\r\n" + "    personas.fecha,\r\n" + "    personas.tipo\r\n"
					+ "FROM paciente\r\n" + "JOIN personas ON paciente.id_pac = personas.id_per\r\n"
					+ "WHERE paciente.id_cuidador =  " + id + ";");

			while (rs.next()) {
				id_per = rs.getInt("id_per");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				usuario = rs.getString("usuario");
				psw = rs.getString("psw");
				dni2 = rs.getString("dni");
				fecha = rs.getString("fecha");
				tipo = rs.getString("tipo");

				PersonaNew persona = new PersonaNew(nombre, apellido, dni2, usuario, psw, fecha, tipo, id_per);
				usuarios.add(persona);
			}

			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

		return usuarios;
	}

	public ArrayList<Sensor1New> cargarSensor(int id, String tipo) {
		
		int id_sen;
		String Ubicacion;
		String Tipo;
		int Dato;
		String Fecha;
		int id_paciente;
		
		ArrayList<Sensor1New> datos = new ArrayList<Sensor1New>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sensor WHERE id_paciente = "+ id+" AND Tipo = '"+tipo+"';");

			while (rs.next()) {
				id_sen = rs.getInt("id_sen");
				Ubicacion = rs.getString("Ubicacion");
				Tipo = rs.getString("Tipo");
				Dato = rs.getInt("Dato");
				Fecha = rs.getString("Fecha");
				id_paciente = rs.getInt("id_paciente");

				Sensor1New s = new Sensor1New(id_sen,Ubicacion,Tipo,Dato,Fecha,id_paciente);
				
				datos.add(s);
			}

			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");

		return datos;
	}

	public void leerTicketsPaciente(ObservableList<TicketsNew> ticketsObservableList, String pWhere, int id) {
		int id_tic, id_medico, id_paciente;
		String texto_Paciente, texto_Medico;
		String fecha_Paciente, fecha_Medico;
		// Date fecha_Medico;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			// todos los tickets que coinsidan con su id y texto medico sea distito de vacio
			ResultSet rs = stmt.executeQuery("SELECT * FROM Ticket where " + pWhere + " = " + id + " AND Texto_Medico is not null;");

			while (rs.next()) {
				// aqui colocar un objeto
				id_tic = rs.getInt("id_tic");
				texto_Paciente = rs.getString("Texto_Paciente");
				fecha_Paciente = rs.getString("Fecha_Paciente");// getDate("Fecha_Paciente");
				texto_Medico = rs.getString("Texto_Medico");
				fecha_Medico = rs.getString("Fecha_Medico");
				id_medico = rs.getInt("id_medico");
				id_paciente = rs.getInt("id_paciente");
				TicketsNew new1 = new TicketsNew(id_tic, id_medico, id_paciente, texto_Paciente, texto_Medico,
						fecha_Paciente, fecha_Medico);
				ticketsObservableList.add(new1);
			}
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println("ERROR: ticket conexion");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		
	}
	public int medicoMasLibre() {

		int id_medico = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery(
					"SELECT\r\n" + 
					"    COUNT(id_pac) AS totalPacientes,\r\n" + 
					"    id_medico\r\n" + 
					"FROM paciente\r\n" + 
					"GROUP BY id_medico\r\n" + 
					"HAVING COUNT(id_pac) = ( SELECT MIN(totalPacientes)\r\n" + 
					"                                        FROM (SELECT\r\n" + 
					"                                                COUNT(id_pac) AS totalPacientes,\r\n" + 
					"                                                id_medico\r\n" + 
					"                                                FROM paciente\r\n" + 
					"                                                GROUP BY id_medico)Conteo_Pacientes)");

			while (rs.next()) {
				id_medico = rs.getInt("id_medico");
			}
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		return id_medico;
	}

	public int medicoMasLibreCui() {
		int id_medico = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mariadb://2.139.176.212/pr_smartgrant", USER, PASS);
			conexion.setAutoCommit(false);

			stmt = conexion.createStatement();
			
			ResultSet rs = stmt.executeQuery(
					"SELECT\r\n" + 
					"    COUNT(id_cui) AS totalcuidadores,\r\n" + 
					"    id_medico\r\n" + 
					"FROM cuidador\r\n" + 
					"GROUP BY id_medico\r\n" + 
					"HAVING COUNT(id_cui) = ( SELECT MIN(totalcuidadores)\r\n" + 
					"                                        FROM (SELECT\r\n" + 
					"                                                COUNT(id_cui) AS totalcuidadores,\r\n" + 
					"                                                id_medico\r\n" + 
					"                                                FROM cuidador\r\n" + 
					"                                                GROUP BY id_medico)Conteo_Cuidadores)");

			while (rs.next()) {
				id_medico = rs.getInt("id_medico");
			}
	
			// destruyo todo consulta conexion y resultset
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Consulta terminada");
		return id_medico;
	}
}