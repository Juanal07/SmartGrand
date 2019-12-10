package Controller;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Cuidador;
import Model.Medico;
import Model.Persona;
import Model.Sensor1Presion;
import Model.Tickets;

public class GsonGeneral {
	public static List<Persona> desserializarJsonAArray() {
		String jsonInString = "usuarios.json";
		List<Persona> listaPersonas = null;
		try (Reader reader = new FileReader(jsonInString)) {
			Gson gson = new Gson();
			Type tipoListaPersonas = new TypeToken<List<Persona>>() {
			}.getType();
			List<Persona> personas = gson.fromJson(reader, tipoListaPersonas);

			listaPersonas = personas;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}

	public static List<Tickets> desserializarJsonAArrayTicket() {
		String jsonInString = "jsonTickets.json";
		List<Tickets> listaTickets = null;
		try (Reader reader = new FileReader(jsonInString)) {
			Gson gson3 = new Gson();
			Type tipoListaTicket = new TypeToken<List<Tickets>>() {
			}.getType();
			List<Tickets> tickets = gson3.fromJson(reader, tipoListaTicket);

			listaTickets = tickets;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaTickets;
	}

	public static List<Medico> desserializarJsonAArrayMedico() {
		String jsonInString = "medicos.json";
		List<Medico> listaMedicos = null;
		try (Reader reader = new FileReader(jsonInString)) {
			Gson gson = new Gson();
			Type tipoListaMedico = new TypeToken<List<Medico>>() {
			}.getType();
			List<Medico> medicos = gson.fromJson(reader, tipoListaMedico);
			listaMedicos = medicos;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaMedicos;
	}

	public static List<Cuidador> desserializarJsonAArrayCuidador() {
		String jsonInString = "cuidadores.json";
		List<Cuidador> listaCuidadores = null;
		try (Reader reader = new FileReader(jsonInString)) {
			Gson gson = new Gson();
			Type tipoListaCuidador = new TypeToken<List<Cuidador>>() {
			}.getType();
			List<Cuidador> cuidadores = gson.fromJson(reader, tipoListaCuidador);
			listaCuidadores = cuidadores;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaCuidadores;
	}

	public static void EscribirJson(String representacionBonita, String ruta) {
		try {
			BufferedWriter bw; // Escribimos la info en el archivo json
			bw = new BufferedWriter(new FileWriter(ruta));
			bw.write(representacionBonita);
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String getMd5(String input) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static ArrayList<Sensor1Presion> desserializarJsonAArraySensor1() {
		List<Sensor1Presion> listaSensorPresion = null;
		try (Reader reader = new FileReader("Sensor1.json")) {
			Gson gson = new Gson();
			Type tipoListasSensor = new TypeToken<List<Sensor1Presion>>() {}.getType();
			ArrayList<Sensor1Presion> sensor1 = gson.fromJson(reader, tipoListasSensor);
			listaSensorPresion =  sensor1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (ArrayList<Sensor1Presion>) listaSensorPresion;
	}
}
