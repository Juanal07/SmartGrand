package controller;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dataBase.Conexion;
import model.Sensor1presion;
import model.Sensor2puerta;
import model.Sensor3caidas;

public class GsonGeneral {
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

	public static boolean seRepiteDnis (String dni) {
		Boolean seRepite = false;
		Conexion conexion = new Conexion();
		List<String> lista = conexion.listaDnis();
		int sizeArray = lista.size();
		int i = 0;
		while(i < sizeArray) {
			if (lista.get(i).equals(dni)) {
				seRepite=true;
				i = i + sizeArray;
			}
			i++;	
		}
		return seRepite;
	}
	
	public static boolean seRepiteUsuario (String user) {
		Boolean seRepite = false;
		Conexion conexion = new Conexion();
		List<String> lista = conexion.listaUsuarios();
		int sizeArray = lista.size();
		int i = 0;
		while(i < sizeArray) {
			if (lista.get(i).equals(user)) {
				seRepite=true;
				i = i + sizeArray;
			}
			i++;	
		}
		return seRepite;
	}
	public static boolean validarNIF(String nif) {
	    boolean correcto = false;
	    Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
	    Matcher matcher = pattern.matcher(nif);
	    if (matcher.matches()) {
	        String letra = matcher.group(2);
	        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	        int index = Integer.parseInt(matcher.group(1));
	        index = index % 23;
	        String reference = letras.substring(index, index + 1);
	        if (reference.equalsIgnoreCase(letra)) {
	            correcto = true;
	        } else {
	            correcto = false;
	        }
	    } else {
	        correcto = false;
	    }
	    return correcto;
	}
}
